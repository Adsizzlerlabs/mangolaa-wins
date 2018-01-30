package com.adsizzler.mangolaa.wins.service

import com.adsizzler.mangolaa.wins.domain.Creative
import io.vertx.core.Future

/**
 * Created by ankushsharma on 11/12/17.
 */
interface CreativeService {

    Future<Creative> findOneById(Integer id)

    Future<Set<Creative>> findAll()

}
