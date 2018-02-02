package com.adsizzler.mangolaa.wins.dataloader

import com.adsizzler.mangolaa.wins.domain.Creative
import com.adsizzler.mangolaa.wins.repository.CreativeRepository
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component


/**
 * Populate cache with data from persistent store on startup
 * Created by ankushsharma on 02/02/18.
 */
@Component
@Slf4j
class DataLoaderOnAppStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private CreativeRepository creativeRepository

    @Override
    void onApplicationEvent(ApplicationReadyEvent event) {
        log.info 'Loading Creatives from DB'
        def creativeDtos = creativeRepository.findAll()
        creativeDtos.each{ dto ->
            def creative = new Creative(dto)
            creativeRepository.putToCache(creative)
        }
        log.info 'Loaded {} creatives from db', creativeDtos.size()
    }
}
