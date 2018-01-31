package com.adsizzler.mangolaa.wins.repository

import com.adsizzler.mangolaa.wins.dataloader.dto.CreativeDTO
import com.adsizzler.mangolaa.wins.domain.Creative
import io.vertx.core.Future

/**
 * Created by ankushsharma on 11/12/17.
 */
interface CreativeRepository {

    /**
     * Find a Creative by its id
     * @param id The id of the creative
     * @return A Future with the creative in it
     */
    Future<Creative> findOneById(Integer id)

    /**
     * Returns all Creatives currently in Cache
     * @return A Future that contains the Set of creatives currently in our local Cache
     */
    Future<Set<Creative>> findAllFromCache()

    /**
     * Evict old creative, and replace with new one
     * @param old Old creative to evict
     * @param newCreative The new Creative object to put to cache
     * @return
     */
    Future<Void> updateCache(Creative old, Creative newCreative)

    /**
     * Put a Creative to Cache
     */
    Future<Void> putToCache(Creative creative)

    /**
     * Save to permanent storage
     * @param dto CreativeDTO to persist to permanent storage
     * @return
     */
    Future<Void> save(CreativeDTO dto)

    /**
     * Get all Creatives from permanent storage
     * @return
     */
    Set<CreativeDTO> findAll()

    /**
     * Evict all creatives from Cache and remove from permanent storage as well. Only for testing purposes.
     * @return
     */
    Future<Void> deleteAll()

}
