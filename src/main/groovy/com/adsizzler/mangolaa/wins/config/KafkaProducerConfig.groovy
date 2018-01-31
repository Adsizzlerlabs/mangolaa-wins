package com.adsizzler.mangolaa.wins.config

import com.adsizzler.mangolaa.wins.util.EnvironmentUtil
import io.vertx.core.Vertx
import io.vertx.kafka.client.producer.KafkaProducer
import org.apache.kafka.common.serialization.ByteArraySerializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import static org.apache.kafka.clients.producer.ProducerConfig.*

/**
 * Created by Ankush on 13/04/17.
 */
@Configuration
class KafkaProducerConfig {

    @Autowired
    Vertx vertx

    @Autowired
    EnvironmentUtil env

    @Bean("kafkaProducer")
    KafkaProducer<String,byte[]> kafkaProducer(){
        Properties config = new Properties()
        String host
        if(env.isDev() || env.isTest()){
            host = "localhost:9092"
        }
        else{
            host = "production kafka cluster address"
        }
        config.put(BOOTSTRAP_SERVERS_CONFIG, host)
        config.put(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class)
        config.put(VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class)
        config.put(ACKS_CONFIG, "1")
        config.put(BATCH_SIZE_CONFIG,"500000")
        config.put(BUFFER_MEMORY_CONFIG, "635544320")

         KafkaProducer
                .create(vertx, config)
    }

}
