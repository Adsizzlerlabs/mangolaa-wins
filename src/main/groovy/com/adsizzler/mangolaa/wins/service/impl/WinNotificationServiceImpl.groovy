package com.adsizzler.mangolaa.wins.service.impl

import com.adsizzler.mangolaa.wins.constants.KafkaTopics
import com.adsizzler.mangolaa.wins.domain.WinNotification
import com.adsizzler.mangolaa.wins.service.WinNotificationService
import com.adsizzler.mangolaa.wins.util.Assert
import com.adsizzler.mangolaa.wins.util.Gzip
import com.adsizzler.mangolaa.wins.util.Json
import groovy.util.logging.Slf4j
import io.vertx.core.Future
import io.vertx.kafka.client.producer.KafkaProducer
import io.vertx.kafka.client.producer.impl.KafkaProducerRecordImpl
import org.springframework.stereotype.Service

/**
 * Created by ankushsharma on 06/12/17.
 */
@Service
@Slf4j
class WinNotificationServiceImpl implements WinNotificationService {

    private final KafkaProducer<String,byte[]> kafkaProducer

    WinNotificationServiceImpl(KafkaProducer<String,byte[]> kafkaProducer) {
        this.kafkaProducer = kafkaProducer
    }

    @Override
    Future<Boolean> queueToKafka(WinNotification win) {
        Assert.notNull(win, 'win cannot be null')
        def future = Future.future()
        def pushed = false

        def payload = Gzip.compress(Json.encode(win))
        def write = new KafkaProducerRecordImpl<String,byte[]>(KafkaTopics.WINS, payload)
        kafkaProducer.write(write, { done ->
            if(done.succeeded()){
                pushed = true
            }
            else{
                future.fail(done.cause())
            }
            future.complete(pushed)
        })
        .exceptionHandler{ Throwable ex ->
            //Don't use future.fail(ex). Rather, look into Kafka retry policies.
            log.error '', ex
            future.complete(false)
        }
        future
    }

}
