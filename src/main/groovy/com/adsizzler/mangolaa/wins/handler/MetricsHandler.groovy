package com.adsizzler.mangolaa.wins.handler

import com.adsizzler.mangolaa.wins.util.Json
import io.vertx.core.Handler
import io.vertx.core.Vertx
import io.vertx.ext.dropwizard.MetricsService
import io.vertx.ext.web.RoutingContext
import org.springframework.stereotype.Component

/**
 * Created by ankushsharma on 04/11/17.
 */
@Component
class MetricsHandler implements Handler<RoutingContext> {

    final MetricsService metricsService
    final Vertx vertx

    MetricsHandler(
            final Vertx vertx,
            final  MetricsService metricsService)
    {
        this.metricsService = metricsService
        this.vertx = vertx
    }

    @Override
     void handle(final RoutingContext routingContext) {
        def response = routingContext.response()
        def jsonMetrics = metricsService.getMetricsSnapshot(vertx)

        def json = Json.encodePretty(jsonMetrics)
        response.putHeader("Content-type","application/json")
                .putHeader("Connection","Keep-Alive")
                .end(json)
    }
}
