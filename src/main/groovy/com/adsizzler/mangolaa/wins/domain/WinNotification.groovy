package com.adsizzler.mangolaa.wins.domain

import com.adsizzler.mangolaa.wins.util.Gzip
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.ToString
import io.vertx.core.json.Json

import java.time.ZonedDateTime

/**
 * Created by ankushsharma on 29/01/18.
 */
@ToString(includePackage = false)
class WinNotification {

    @JsonProperty(value = 'timestamp', required = true)
    private final ZonedDateTime timestamp

    @JsonProperty(value = 'markup')
    private final String markup

    @JsonProperty(value = 'crId', required = true)
    private final Integer creativeId

    @JsonProperty(value = 'campId', required = true)
    private final Integer campaignId

    @JsonProperty(value = "advId", required = true)
    private final Integer advId

    @JsonProperty(value = "clientId", required = true)
    private final Integer clientId

    @JsonProperty(value = 'bidReqId')
    private final String bidReqId

    @JsonProperty(value = 'bidRespId')
    private final String bidRespId

    @JsonProperty(value = 'impId')
    private final String impId

    @JsonProperty(value = 'adId')
    private final String adId

    @JsonProperty(value = 'clearingPrice')
    private final Float clearingPrice

    @JsonProperty(value = 'seatId')
    private final String seatId

    @JsonProperty(value = 'cur')
    private final String cur

    @JsonProperty(value = 'mbr')
    private final Double mbr

    @JsonProperty(value = 'lossCode')
    private final Integer lossCode


    WinNotification(Map fields){
        this.timestamp = fields['timestamp'] as ZonedDateTime
        this.markup = fields['markup'] as String
        this.creativeId = fields['creativeId'] as Integer
        this.campaignId = fields['campaignId'] as Integer
        this.advId = fields['advId'] as Integer
        this.clientId = fields['clientId'] as Integer
        this.bidReqId = fields['bidReqId'] as String
        this.bidRespId = fields['bidRespId'] as String
        this.impId = fields['impId'] as String
        this.adId = fields['adId'] as String
        this.cur = fields['cur'] as String
        this.mbr = fields['mbr'] as Double
        this.lossCode = fields['lossCode'] as Integer
        this.clearingPrice = fields['clearingPrice'] as Integer
        this.seatId = fields['seatId'] as String
    }

}
