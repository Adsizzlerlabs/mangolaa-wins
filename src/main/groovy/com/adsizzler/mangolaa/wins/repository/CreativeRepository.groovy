package com.adsizzler.mangolaa.wins.repository

import com.adsizzler.mangolaa.wins.dataloader.dto.CreativeDTO
import com.adsizzler.mangolaa.wins.domain.Creative
import io.vertx.core.Future

/**
 * Created by ankushsharma on 11/12/17.
 */
interface CreativeRepository {

    Future<Creative> findOneById(Integer id)

    Future<Set<Creative>> findAllFromCache()

    Future<Void> updateCache(Creative old, Creative newCreative)

    Set<CreativeDTO> findAll()

}
