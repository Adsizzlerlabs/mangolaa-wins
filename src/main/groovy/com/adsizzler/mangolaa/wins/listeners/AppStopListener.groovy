package com.adsizzler.mangolaa.wins.listeners

import groovy.util.logging.Slf4j
import io.vertx.kafka.client.producer.KafkaProducer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextClosedEvent
import org.springframework.stereotype.Component

/**
 * Do certain stuff when the app closes. For example, close up connections with underlying caches or db
 * Created by ankushsharma on 06/11/17.
 */
@Component
@Slf4j
class AppStopListener implements ApplicationListener<ContextClosedEvent> {

    @Autowired
    KafkaProducer<String,byte[]> kafka

    @Override
    void onApplicationEvent(ContextClosedEvent event) {
        kafka.close{ closed ->
            if(closed.succeeded()){
                log.info 'Closed connection with Kafka successfully'
            }
            else{
                log.info 'Could not close Kafka connection {}', closed.cause().message
            }
        }
    }
}
