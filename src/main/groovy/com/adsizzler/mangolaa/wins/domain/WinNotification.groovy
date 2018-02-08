package com.adsizzler.mangolaa.wins.domain

import com.adsizzler.mangolaa.wins.jackson.serializer.UUIDSerializer
import com.adsizzler.mangolaa.wins.jackson.serializer.ZonedDateTimeSerializer
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import groovy.transform.ToString

import java.time.ZonedDateTime

/**
 * Created by ankushsharma on 29/01/18.
 */
@ToString(includePackage = false)
class WinNotification {

    @JsonProperty(value = 'uuid', required = true)
    @JsonSerialize(using = UUIDSerializer)
    final UUID uuid

    @JsonProperty(value = 'timestamp', required = true)
    @JsonSerialize(using = ZonedDateTimeSerializer)
    final ZonedDateTime timestamp

    @JsonProperty(value = 'markup')
    final String markup

    @JsonProperty(value = 'crId', required = true)
    final Integer creativeId

    @JsonProperty(value = 'campId', required = true)
    final Integer campaignId

    @JsonProperty(value = 'srcId', required = true)
    final Integer sourceId

    @JsonProperty(value = "advId", required = true)
    final Integer advId

    @JsonProperty(value = "clientId", required = true)
    final Integer clientId

    @JsonProperty(value = 'bidReqId')
    final String bidReqId

    @JsonProperty(value = 'bidRespId')
    final String bidRespId

    @JsonProperty(value = 'impId')
    final String impId

    @JsonProperty(value = 'adId')
    final String adId

    @JsonProperty(value = 'clearingPrice')
    final Float clearingPrice

    @JsonProperty(value = 'seatId')
    final String seatId

    @JsonProperty(value = 'cur')
    final String cur

    @JsonProperty(value = 'mbr')
    final Double mbr

    @JsonProperty(value = 'lossCode')
    final Integer lossCode

    WinNotification(Map fields){
        this.uuid = fields['uuid'] as UUID
        this.timestamp = fields['timestamp'] as ZonedDateTime
        this.markup = fields['markup'] as String
        this.creativeId = fields['creativeId'] as Integer
        this.campaignId = fields['campaignId'] as Integer
        this.sourceId = fields['sourceId'] as Integer
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
