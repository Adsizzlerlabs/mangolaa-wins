package com.adsizzler.mangolaa.wins.handler

import com.adsizzler.mangolaa.wins.domain.AdMarkupBuilder
import com.adsizzler.mangolaa.wins.domain.WinNotification
import com.adsizzler.mangolaa.wins.exceptions.ResourceNotFoundException
import com.adsizzler.mangolaa.wins.service.CreativeService
import com.adsizzler.mangolaa.wins.service.WinNotificationService
import com.adsizzler.mangolaa.wins.util.DateTime
import groovy.util.logging.Slf4j
import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext
import org.springframework.stereotype.Component

import static com.adsizzler.mangolaa.wins.constants.HttpHeaderValues.TEXT_HTML
import static io.vertx.core.http.HttpHeaders.CONTENT_TYPE


/**
 * Created by ankushsharma on 05/12/17.
 */
@Component
@Slf4j
class WinsNotificationHandler implements Handler<RoutingContext> {

    private final WinNotificationService winNotificationService
    private final CreativeService creativeService

    WinsNotificationHandler(
            WinNotificationService winNotificationService,
            CreativeService creativeService
    )
    {
        this.winNotificationService = winNotificationService
        this.creativeService = creativeService
    }

    /**
     * Handle the win event. Return the markup if a valid creative id is passed, and push the WinNotification event to Kafka
     * @param rc Routing Context
     */
    @Override
    void handle(RoutingContext rc) {

        def req = rc.request()
        def resp = rc.response()

        def queryParams = req.params()

        //From path params
        def creativeId = req.getParam('cr_id') as Integer
        def sourceId = req.getParam('src_id') as Integer

        //Macros from Query param
        def bidReqId = queryParams.get('bid_req_id')
        def bidRespId = queryParams.get('bid_resp_id')
        def impId = queryParams.get('imp_id')
        def seatId = queryParams.get('seat_id')
        def adId = queryParams.get('ad_id')
        def cur = queryParams.get('cur')
        def price = queryParams.get('price')
        def mbr = queryParams.get('mbr')
        def lossCode = queryParams.get('loss')

        def creativeFuture = creativeService.findOneById(creativeId)
        creativeFuture.setHandler{ ar ->
            if(ar.succeeded()){
                def timestamp = DateTime.nowAsUtc()
                def creative = ar.result()
                if(creative){
                    def markupTemplate = creative.markupTemplate

                    //Build the markup to send from the markup template by filling macros
                    def campaignId = creative.campaignId
                    def advId = creative.advertiserId
                    def clientId = creative.clientId

                    def markup = new AdMarkupBuilder(
                            markupTemplate : markupTemplate,
                            campaignId : campaignId,
                            sourceId:  sourceId,
                            creativeId:  creativeId,
                            advId:   advId,
                            clientId:  clientId,
                            bidReqId : bidReqId,
                            bidRespId:  bidRespId
                    ).build()

                    //Set response values
                    resp.putHeader(CONTENT_TYPE, TEXT_HTML)
                        .end(markup)

                    //Push WinNotification to Kafka
                    def winNotification = new WinNotification(
                            timestamp : timestamp,
                            creativeId : creativeId,
                            campaignId : campaignId,
                            advId : advId,
                            sourceId : sourceId,
                            clientId : clientId,
                            bidReqId : bidReqId,
                            bidRespId : bidRespId,
                            impId : impId,
                            seatId : seatId,
                            adId : adId,
                            cur : cur,
                            clearingPrice : price,
                            mbr : mbr,
                            lossCode : lossCode
                    )
                    winNotificationService.queueToKafka(winNotification)
                }
                else{
                    rc.fail(new ResourceNotFoundException("Creative with id $creativeId doesnt exist"))
                }
            }
            else{
                rc.fail(ar.cause())
            }
        }
    }

}
