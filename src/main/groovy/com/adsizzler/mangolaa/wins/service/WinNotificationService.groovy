package com.adsizzler.mangolaa.wins.service

import com.adsizzler.mangolaa.wins.domain.WinNotification
import io.vertx.core.Future

/**
 * Created by ankushsharma on 06/12/17.
 */
interface WinNotificationService {

    Future<Void> queueToKafka(WinNotification wins)
}
