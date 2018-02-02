package com.adsizzler.mangolaa.wins.config

import com.adsizzler.mangolaa.wins.domain.Creative
import com.googlecode.cqengine.ConcurrentIndexedCollection
import com.googlecode.cqengine.IndexedCollection
import com.googlecode.cqengine.index.hash.HashIndex
import com.googlecode.cqengine.index.unique.UniqueIndex
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * We use CQEngine as our Creatives cache
 * Created by ankushsharma on 09/11/17.
 */
@Configuration
class CQEngineConfig {

    @Bean
    IndexedCollection<Creative> creativesCache(){
        def indexedCollection = new ConcurrentIndexedCollection<Creative>()
        indexedCollection.addIndex(UniqueIndex.onAttribute(Creative.ID))
        indexedCollection
    }
}
