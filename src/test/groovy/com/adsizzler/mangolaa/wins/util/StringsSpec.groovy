package com.adsizzler.mangolaa.wins.util

import com.adsizzler.mangolaa.wins.BaseSpockSpec
import com.adsizzler.mangolaa.wins.domain.enums.Status

/**
 * Created by Ankush on 15/04/17.
 */
class StringsSpec extends BaseSpockSpec {

    def "Does String have text?"(){
        when:
            def result = Strings.hasText(str)
        then:
            result == expectedResult
        where:
            str |  expectedResult
            "A" | true
            null | false
            " "  | false
            ""  | false
            " trim " | true
    }

    def "Build String from a collection of Strings" (){
        when:
            def result = Strings.build(array)
        then:
            result == expectedResult
        where:
            array                                           | expectedResult
            ["a"," " ,"b"].toArray()                        | "a b"
            [Status.ACTIVE, ' ', Status.DEACTIVE].toArray() | 'ACTIVE DEACTIVE'
    }
}
