package com.adsizzler.mangolaa.wins.repository.impl

import com.adsizzler.mangolaa.wins.dataloader.dto.CreativeDTO
import com.adsizzler.mangolaa.wins.dataloader.repository.CreativePersistentStore
import com.adsizzler.mangolaa.wins.domain.Creative
import com.adsizzler.mangolaa.wins.repository.CreativeRepository
import com.adsizzler.mangolaa.wins.util.Assert
import com.googlecode.cqengine.IndexedCollection
import groovy.util.logging.Slf4j
import io.vertx.core.Future
import org.springframework.stereotype.Repository

import static com.googlecode.cqengine.query.QueryFactory.equal

/**
 * Created by ankushsharma on 11/12/17.
 */
@Repository
@Slf4j
class CreativeRepositoryImpl implements CreativeRepository {

    private final IndexedCollection<Creative> creativeCache
    private final CreativePersistentStore creativePersistentStore

    CreativeRepositoryImpl(
            IndexedCollection<Creative> creativeCache,
            CreativePersistentStore creativePersistentStore
    )
    {
        this.creativeCache = creativeCache
        this.creativePersistentStore = creativePersistentStore
    }

    @Override
    Future<Creative> findOneById(Integer id) {
        Assert.notNull(id , 'id cannot be null')

        def future = Future.future()
        def query = equal(Creative.ID, id)
        def resultSet = creativeCache.retrieve(query)
        //Guaranteed to contain only 1 element, as each creative is assigned to a unique id
        if(resultSet) {
            def creative = resultSet.find()
            log.debug 'Creative {}' , creative
            future.complete(creative)
        }
        else {
            //complete with a null
            future.complete()
        }
        future
    }

    @Override
    Future<Set<Creative>> findAllFromCache() {
        def future = Future.future()
        future.complete(creativeCache.toSet())
        future
    }

    @Override
    Future<Void> updateCache(Creative old, Creative newCreative) {
        Assert.notNull(old, 'old Creative cannot be null')
        Assert.notNull(newCreative, 'newCreative cannot be null')
        def future = Future.future()
        try{
            creativeCache.update([old], [newCreative])
            future.complete()
        }
        catch(ex){
            future.fail(ex)
        }
        future
    }

    @Override
    Set<CreativeDTO> findAll() {
        creativePersistentStore.findAll()?.toSet()
    }
}
