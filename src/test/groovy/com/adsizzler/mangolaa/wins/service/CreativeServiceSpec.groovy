package com.adsizzler.mangolaa.wins.service

import com.adsizzler.mangolaa.wins.BaseSpockSpec
import com.adsizzler.mangolaa.wins.dataloader.repository.CreativePersistentStore
import com.adsizzler.mangolaa.wins.domain.Creative
import com.adsizzler.mangolaa.wins.domain.enums.Status
import com.adsizzler.mangolaa.wins.repository.CreativeRepository
import com.googlecode.cqengine.IndexedCollection
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by ankushsharma on 30/01/18.
 */
class CreativeServiceSpec extends BaseSpockSpec {

    @Autowired
    private CreativeService creativeService

    @Autowired
    private CreativeRepository repository

    /**
     * Save to Cache before running tests
     */
    def setup(){
        def dtos = repository.findAll()
        dtos.each { dto ->
            def creative = new Creative(dto)
            repository.putToCache(creative)
        }
    }

    def "Find a Creative with id 1"(){
        given :
            def id = 1

        when :
            def creative = creativeService.findOneById(id).result()
        
        then :
            creative == new Creative(
                      id :  1,
                      status : Status.ACTIVE,
                      campaignId : 2,
                      advertiserId : 3,
                      clientId:  1
            )
    }

    def "Find all creatives. Only 2 creatives must be returned as per data.sql file"(){

        when :
            def creatives = creativeService.findAll().result().toList().sort{ it.id }

        then :
            creatives == [
                    new Creative(
                            id :  1,
                            status : Status.ACTIVE,
                            campaignId : 2,
                            advertiserId : 3,
                            clientId:  1
                    ),
                    new Creative(
                            id :  2,
                            status : Status.DEACTIVE,
                            campaignId : 2,
                            advertiserId : 3,
                            clientId:  1
                    )
            ]
    }

    /**
     * Remove all dtos and evict from Cache
     */
    def cleanup(){
        repository.deleteAll()
    }
}
