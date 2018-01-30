package com.adsizzler.mangolaa.wins.config

import groovy.util.logging.Slf4j
import io.vertx.core.Vertx
import io.vertx.core.VertxOptions
import io.vertx.core.http.HttpServer
import io.vertx.ext.dropwizard.DropwizardMetricsOptions
import io.vertx.ext.web.Router
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Created by ankushsharma on 01/09/17.
 */
@Configuration
@Slf4j
class VertxConfig {

    @Bean
    Vertx vertx(){
        Vertx.vertx(
                new VertxOptions()
                    //This is not at all an optimal setting, but then we are loading our db-ip file on startup on the Event loop thread
                    .setBlockedThreadCheckInterval(1)
                    .setMetricsOptions(
                        new DropwizardMetricsOptions()
                                .setEnabled(true)
            )
        )
        .exceptionHandler{ ex ->
            log.error 'Unhandeled Exception {}', ex
        }
    }

    @Bean
    HttpServer httpServer(){
        vertx().createHttpServer().requestHandler(router().&accept).listen(8080)
    }

    @Bean
    Router router(){
        Router.router(vertx())
    }
}

