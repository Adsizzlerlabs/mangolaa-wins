package com.adsizzler.mangolaa.wins.domain.enums

import com.adsizzler.mangolaa.wins.BaseSpockSpec

/**
 * Created by ankushsharma on 31/01/18.
 */
class StatusSpec extends BaseSpockSpec {

    def "resolve status from int value"(){
        when :
            def status = Status.from(code)

        then :
            status == expectedResult

        where :
            code || expectedResult
            1    || Status.ACTIVE
            2    || Status.DEACTIVE
            3    || Status.PAUSED
            1212 || Status.UNKNOWN
    }

    def "resolve status from string value"(){
        when :
            def status = Status.from(string)

        then :
            status == expectedResult

        where :
        string          || expectedResult
            "active"    || Status.ACTIVE
            "ACTIVE"    || Status.ACTIVE
            "deactive"  || Status.DEACTIVE
            "De-Active" || Status.DEACTIVE
            "Random"    || Status.UNKNOWN
            null        || Status.UNKNOWN
    }


}
