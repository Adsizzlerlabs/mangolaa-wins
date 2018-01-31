package com.adsizzler.mangolaa.wins.service

import com.adsizzler.mangolaa.wins.BaseSpockSpec
import com.adsizzler.mangolaa.wins.domain.WinNotification
import org.springframework.beans.factory.annotation.Autowired
import spock.util.concurrent.AsyncConditions

import java.time.ZonedDateTime

/**
 * Created by ankushsharma on 31/01/18.
 */
class WinNotificationServiceSpec extends BaseSpockSpec {

    @Autowired
    WinNotificationService winNotificationService

    AsyncConditions conditions

    def "method #winNotificationService.queueToKakfa return true if WinNotification was pushed to Kafka"(){
        given :
            conditions = new AsyncConditions(1)
            def win = new WinNotification(
                    timestamp : ZonedDateTime.now(),
                    markup : "some ridiculous markup",
                    creativeId : 1 ,
                    campaignId : 2,
                    advId : 3,
                    clientId : 1,
                    bidReqId : UUID.randomUUID().toString(),
                    bidRespId:  UUID.randomUUID().toString(),
                    impId : UUID.randomUUID().toString(),
                    seatId : UUID.randomUUID().toString(),
                    clearingPrice : 1.2,
                    cur : "USD",
                    mbr : 12.1,
                    lossCode : 1
            )

        expect :

            winNotificationService.queueToKafka(win).setHandler{ ar ->
                conditions.evaluate{
                    assert ar.result()
                }
            }
            conditions.await()
    }

    def "throw IllegalArgumentException when null is passed to #winNotificationService.queueToKafka"(){
        when :
            winNotificationService.queueToKafka(null)

        then :
            thrown IllegalArgumentException
    }

}
