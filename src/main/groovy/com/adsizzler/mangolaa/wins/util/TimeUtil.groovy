package com.adsizzler.mangolaa.wins.util

import java.time.ZonedDateTime

/**
 * Created by ankushsharma on 03/11/17.
 */
class TimeUtil {

    static String buildAsString(ZonedDateTime dateTime){
        Assert.notNull(dateTime, 'dateTime cannot be null')
        def month = dateTime.monthValue
        def day = dateTime.dayOfMonth
        def year = dateTime.year
        def hour = dateTime.hour
        def minute = dateTime.minute
        def seconds = dateTime.second

        Strings.build(
                year,"-",
                (month <= 9 ? "0" + month : month),"-",
                (day <= 9 ? "0" + day : day)," ",
                (hour <= 9 ? "0" + hour : hour), ":",
                (minute <= 9 ? "0" + minute : minute) ,":",
                (seconds <= 9 ? "0" + seconds : seconds)
        )

    }

}
