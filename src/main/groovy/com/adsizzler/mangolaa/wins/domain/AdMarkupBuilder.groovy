package com.adsizzler.mangolaa.wins.domain


/**
 * Created by ankushsharma on 30/01/18.
 */
class AdMarkupBuilder {

    private final String markupTemplate
    private final Integer campaignId
    private final Integer sourceId
    private final Integer creativeId
    private final Integer advId
    private final Integer clientId
    private final String bidReqId
    private final String bidRespId

    AdMarkupBuilder(Map fields){
        this.markupTemplate = fields['markupTemplate'] as String
        this.campaignId = fields['campaignId'] as Integer
        this.creativeId = fields['creativeId'] as Integer
        this.advId = fields['advId'] as Integer
        this.sourceId = fields['sourceId'] as Integer
        this.clientId = fields['clientId'] as Integer
        this.bidReqId = fields['bidReqId'] as String
        this.bidRespId = fields['bidRespId'] as String
    }

    String build(){
        String campaignIdStr = campaignId
        String sourceIdStr = sourceId
        String creativeIdStr = creativeId
        String clientIdStr = clientId
        String advIdStr = advId

        markupTemplate
                //For Clicks and impressions tracking
                .replace("{camp_id}", campaignIdStr)
                .replace("{src_id}", sourceIdStr)
                .replace("{creative_id}", creativeIdStr)
                .replace("{cl_id}", clientIdStr)
                .replace("{adv_id}", advIdStr)

                //Bid Req and Bid Response
                .replace("{bid_req_id}", bidReqId)
                .replace("bid_resp_id}", bidRespId)
    }
}
