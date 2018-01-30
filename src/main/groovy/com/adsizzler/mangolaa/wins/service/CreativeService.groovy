package com.adsizzler.mangolaa.wins.service

import com.adsizzler.mangolaa.wins.domain.Creative
import io.vertx.core.Future

/**
 * Created by ankushsharma on 11/12/17.
 */
interface CreativeService {

    /**
     * Find a Creative by its id
     * @param id The id of the creative
     * @return A Future that contains the creative, or an exception
     */
    Future<Creative> findOneById(Integer id)


    /**
     * @return A Future that contains the Set of creatives currently in the system, or an exception
     */
    Future<Set<Creative>> findAll()

}
