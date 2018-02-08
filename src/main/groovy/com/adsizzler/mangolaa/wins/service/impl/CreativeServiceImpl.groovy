package com.adsizzler.mangolaa.wins.service.impl

import com.adsizzler.mangolaa.wins.domain.Creative
import com.adsizzler.mangolaa.wins.repository.CreativeRepository
import com.adsizzler.mangolaa.wins.service.CreativeService
import com.adsizzler.mangolaa.wins.util.Assert
import groovy.util.logging.Slf4j
import io.vertx.core.Future
import org.springframework.stereotype.Service

/**
 * Created by ankushsharma on 11/12/17.
 */
@Service
@Slf4j
class CreativeServiceImpl implements CreativeService {

    private final CreativeRepository creativeRepository

    CreativeServiceImpl(CreativeRepository creativeRepository){
        this.creativeRepository = creativeRepository
    }

    @Override
    Future<Creative> findOneById(Integer id) {
        Assert.notNull(id, 'creativeId cannot be null')
        creativeRepository.findOneById(id)
    }

    @Override
    Future<Set<Creative>> findAll() {
        creativeRepository.findAllFromCache()
    }

}
