package com.adsizzler.mangolaa.wins.handler

import com.adsizzler.mangolaa.wins.exceptions.ResourceNotFoundException
import com.adsizzler.mangolaa.wins.util.Json
import groovy.util.logging.Slf4j
import io.netty.handler.codec.http.HttpResponseStatus
import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext
import org.springframework.stereotype.Component

import static com.adsizzler.mangolaa.wins.constants.HttpHeaderValues.APPLICATION_JSON
import static io.vertx.core.http.HttpHeaders.CONTENT_TYPE

/**
 * Failure handler for all handlers. If an exception is thrown in any of our handlers, we catch it here and show the user an appropriate message
 * We choose not to return Http Status 4xx or 5xx because if we do, the Load Balancer might remove the instance from its registry of nodes.
 * Created by ankushsharma on 03/11/17.
 */
@Component
@Slf4j
class GlobalFailureHandler implements Handler<RoutingContext> {

    static final String GLOBAL_ERROR_MSG = "Internal Server Error"

    @Override
    void handle(RoutingContext rc) {
        def resp = rc.response()
        def exception = rc.failure()
        def msg
        switch(exception){
            case ResourceNotFoundException : msg = exception.message; break
            default : msg = GLOBAL_ERROR_MSG
                      log.error '', msg
        }

        def responseMsg = Json.encodePretty([msg: msg])
        resp.putHeader(CONTENT_TYPE, APPLICATION_JSON)
            .setStatusCode(HttpResponseStatus.OK.code())
            .end(responseMsg)
    }
}
