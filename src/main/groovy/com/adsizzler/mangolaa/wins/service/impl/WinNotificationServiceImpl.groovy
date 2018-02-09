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

import javax.annotation.PreDestroy
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Created by ankushsharma on 06/12/17.
 */
@Service
@Slf4j
class WinNotificationServiceImpl implements WinNotificationService {

    private static final ExecutorService serializationExecutor
    private final KafkaProducer<String,byte[]> kafkaProducer

    WinNotificationServiceImpl(KafkaProducer<String,byte[]> kafkaProducer) {
        this.kafkaProducer = kafkaProducer
    }

    static{
        final int threads = Runtime.getRuntime().availableProcessors()
        log.info 'Initializing {} thread pool with {} threads', this.name, threads
        serializationExecutor = Executors.newFixedThreadPool(threads)
    }


    @PreDestroy
    void cleanup(){
        log.info 'Shutting down executor pool for class {}', this.name
        serializationExecutor.shutdown()
    }

    @Override
    Future<Boolean> queueToKafka(WinNotification win) {
        Assert.notNull(win, 'win cannot be null')
        def future = Future.future()

        //Keeping serialization out of EventLoop's path
        serializationExecutor.execute {
            def payload = Gzip.compress(Json.encode(win))
            def write = new KafkaProducerRecordImpl<String,byte[]>(KafkaTopics.WINS, payload)
            kafkaProducer.write(write, { done ->
                def pushed = false
                if(done.succeeded()) {
                    pushed = true
                }
                future.complete(pushed)
            })
            .exceptionHandler{ Throwable ex ->
                // Don't use future.fail(ex). Rather, look into Kafka retry policies.
                log.error '', ex
            }
        }
        future
    }

}
