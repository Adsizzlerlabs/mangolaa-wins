package com.adsizzler.mangolaa.wins.handler

import com.adsizzler.mangolaa.wins.service.CreativeService
import com.adsizzler.mangolaa.wins.util.Json
import groovy.util.logging.Slf4j
import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext
import org.springframework.stereotype.Component

import javax.annotation.PreDestroy
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

import static com.adsizzler.mangolaa.wins.constants.HttpHeaderValues.getAPPLICATION_JSON
import static io.vertx.core.http.HttpHeaders.CONTENT_TYPE

/**
 * Handler that returns a JSON view of the set of creatives in our in memory cache. Used mostly for debugging
 * Created by ankushsharma on 30/01/18.
 */
@Component
@Slf4j
class CreativesViewHandler implements Handler<RoutingContext> {

    private static final ExecutorService executor = Executors.newSingleThreadExecutor()
    private final CreativeService creativeService

    CreativesViewHandler(CreativeService creativeService){
        this.creativeService = creativeService
    }

    /**
     * Perform certain operations before destroying this bean
     */
    @PreDestroy
    void cleanup(){
        log.info 'Shutting down ExecutoService for class {}', this.class.name
        executor.shutdown()
    }

    @Override
    void handle(RoutingContext rc) {
        def resp = rc.response()
        def creativesFuture = creativeService.findAll()
        creativesFuture.setHandler{ ar ->
            if(ar.succeeded()){
                executor.execute{
                    def creatives = ar.result()
                    def json = Json.toPrettyJson([result : creatives])
                    resp.putHeader(CONTENT_TYPE, APPLICATION_JSON)
                        .end(json)
                }
            }
            else{
                rc.fail(ar.cause())
            }
        }
    }
}
