package com.adsizzler.mangolaa.wins.service

import com.adsizzler.mangolaa.wins.BaseSpockSpec
import com.adsizzler.mangolaa.wins.domain.Creative
import com.adsizzler.mangolaa.wins.domain.enums.Status
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by ankushsharma on 30/01/18.
 */
class CreativeServiceSpec extends BaseSpockSpec {

    @Autowired
    private CreativeService creativeService

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
                      advertiserId : 1,
                      clientId:  3
            )


    }


}
