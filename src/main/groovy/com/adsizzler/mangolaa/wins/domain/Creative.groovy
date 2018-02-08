package com.adsizzler.mangolaa.wins.domain

import com.adsizzler.mangolaa.wins.dataloader.entity.CreativeEntity
import com.adsizzler.mangolaa.wins.domain.enums.Status
import com.adsizzler.mangolaa.wins.util.Assert
import com.fasterxml.jackson.annotation.JsonIgnore
import com.googlecode.cqengine.attribute.SimpleAttribute
import com.googlecode.cqengine.query.option.QueryOptions
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString


/**
 * Created by ankushsharma on 11/12/17.
 */
@ToString(includePackage = false)
@EqualsAndHashCode
class Creative {

     final Integer id
     final Status status
     final String markupTemplate
     final Integer campaignId
     final Integer advertiserId
     final Integer clientId

     @JsonIgnore
     boolean isActive(){
         this.status == Status.ACTIVE
     }

    /**
     * Create an instance of Creative
     * @param fields The Map in which the keys have the same name as the name of the fields in this class
     */
    Creative(Map fields){
        this.id = fields['id'] as Integer
        this.status = fields['status'] as Status
        this.markupTemplate = fields['markupTemplate'] as String
        this.campaignId = fields['campaignId'] as Integer
        this.advertiserId = fields['advertiserId'] as Integer
        this.clientId = fields['clientId'] as Integer
    }

    /**
     * Create an instance of Creative from a CreativeDTO object
     * @param dto The dto object
     */
    Creative(CreativeEntity dto){
        Assert.notNull(dto ,'Creative dto cannot be null')

        this.id = dto.id
        this.status = dto.status
        this.markupTemplate = dto.markupTemplate
        this.campaignId = dto.campaignId
        this.advertiserId = dto.advId
        this.clientId = dto.clientId
    }

    /**
     * Use creativeId as an Index in CQEngine
     */
    static final ID = new SimpleAttribute<Creative, Integer>() {

        @Override
        Integer getValue(Creative creative, QueryOptions queryOptions) {
            creative.id
        }

    }

}
