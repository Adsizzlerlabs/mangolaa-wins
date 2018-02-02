package com.adsizzler.mangolaa.wins.verticle

import com.adsizzler.mangolaa.wins.handler.CreativesViewHandler
import com.adsizzler.mangolaa.wins.handler.GlobalFailureHandler
import com.adsizzler.mangolaa.wins.handler.MetricsHandler
import com.adsizzler.mangolaa.wins.handler.WinsNotificationHandler
import groovy.util.logging.Slf4j
import io.vertx.core.AbstractVerticle
import io.vertx.ext.web.Router
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE

/**
 * Register all Handlers and their urls with verticle.
 * Created by ankushsharma on 05/11/17.
 */
@Component
@Slf4j
//So that more than one verticle could be deployed
@Scope(SCOPE_PROTOTYPE)
class WinNotificationsVerticle extends AbstractVerticle {

    private final Router router
    private final MetricsHandler metricsHandler
    private final GlobalFailureHandler failureHandler
    private final WinsNotificationHandler handler
    private final CreativesViewHandler creativesViewHandler

    WinNotificationsVerticle(
            Router router,
            MetricsHandler metricsHandler,
            GlobalFailureHandler failureHandler,
            WinsNotificationHandler handler,
            CreativesViewHandler creativesViewHandler
    )
    {
        this.router = router
        this.metricsHandler = metricsHandler
        this.failureHandler = failureHandler
        this.handler = handler
        this.creativesViewHandler = creativesViewHandler
    }

    @Override
    void start(){

        //For example, our endpoint can be wins.mangolaa.com/?queryParams
        router.get('/')
              .handler(handler)
              .failureHandler(failureHandler)

        router.get('/metrics')
              .handler(metricsHandler)
              .failureHandler(failureHandler)

        router.get('/creatives')
              .handler(creativesViewHandler)
              .failureHandler(creativesViewHandler)
    }
}
