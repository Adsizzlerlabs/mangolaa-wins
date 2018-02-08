package com.adsizzler.mangolaa.wins.domain

import com.adsizzler.mangolaa.wins.util.Assert
import com.adsizzler.mangolaa.wins.util.Strings


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
        Assert.notEmptyString(markupTemplate,'adMarkupTemplate cannot be null or empty')

        String campaignIdStr = campaignId
        String sourceIdStr = sourceId
        String creativeIdStr = creativeId
        String clientIdStr = clientId
        String advIdStr = advId

        markupTemplate
                //For Clicks and impressions tracking
                .replace("{camp_id}", normalize(campaignIdStr))
                .replace("{src_id}", normalize(sourceIdStr))
                .replace("{creative_id}", normalize(creativeIdStr))
                .replace("{cl_id}", normalize(clientIdStr))
                .replace("{adv_id}", normalize(advIdStr))

                //Bid Req and Bid Response
                .replace("{bid_req_id}", normalize(bidReqId))
                .replace("bid_resp_id}", normalize(bidRespId))
    }

    private static normalize(String param){
        if(!Strings.hasText(param)){
            return Strings.EMPTY
        }
        return URLEncoder.encode(param , "UTF-8")
    }

    public static void main(String[] args) {
        def s = "https://google.com/"

        s.replace("{a}", null)
    }
}
