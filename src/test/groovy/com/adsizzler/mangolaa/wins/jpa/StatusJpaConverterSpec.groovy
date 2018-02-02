package com.adsizzler.mangolaa.wins.jpa

import com.adsizzler.mangolaa.wins.BaseSpockSpec
import com.adsizzler.mangolaa.wins.domain.enums.Status

/**
 * Created by ankushsharma on 01/02/18.
 */
class StatusJpaConverterSpec extends BaseSpockSpec {

    def "convert enum representation of Status to its code when inserting a Status enum to db"(){
        when :
            def result = new StatusJpaConverter().convertToDatabaseColumn(status)

        then :
            result == expectedResult

        where :
            status          || expectedResult
            Status.ACTIVE   || 1
            Status.DEACTIVE || 2
            Status.UNKNOWN  || 0
    }

    def "convert int code to its Status enum representation"(){
        when :
            def result = new StatusJpaConverter().convertToEntityAttribute(code)

        then :
            result == expectedResult

        where :
            code            || expectedResult
            1               || Status.ACTIVE
            2               || Status.DEACTIVE
            null            || Status.UNKNOWN
    }



}
