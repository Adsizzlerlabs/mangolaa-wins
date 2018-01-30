package com.adsizzler.mangolaa.wins.util

import java.time.ZoneId
import java.time.ZonedDateTime

/**
 * Created by ankushsharma on 08/12/17.
 */
class DateTime {

    static ZonedDateTime nowAsUtc(){
        ZonedDateTime.now(ZoneId.of("Asia/Kolkata"))
    }
}
