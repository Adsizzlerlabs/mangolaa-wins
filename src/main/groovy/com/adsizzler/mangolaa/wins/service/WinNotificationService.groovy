package com.adsizzler.mangolaa.wins.service

import com.adsizzler.mangolaa.wins.domain.WinNotification
import io.vertx.core.Future

/**
 * Created by ankushsharma on 06/12/17.
 */
interface WinNotificationService {

    /**
     * Convert POJO -> JSON -> GZIP . Push gzip byte[] to Kafka. Gzip will save Network bandwidth
     * @param win WinNotification to push to Kafka
     * @return Future with a boolean value of true if @param win was successfully pushed to Kafka, and false otherwise
     */
    Future<Boolean> queueToKafka(WinNotification win)

}
