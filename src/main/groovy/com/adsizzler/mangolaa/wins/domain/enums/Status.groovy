package com.adsizzler.mangolaa.wins.domain.enums

import com.adsizzler.mangolaa.wins.jackson.deserializer.StatusDeserializer
import com.adsizzler.mangolaa.wins.util.Strings
import com.fasterxml.jackson.databind.annotation.JsonDeserialize

/**
 * Created by ankushsharma on 03/11/17.
 */
@JsonDeserialize(using = StatusDeserializer)
enum Status {

    UNKNOWN(0),
    ACTIVE(1),
    DEACTIVE(2),
    PAUSED(3)

    final int code

    Status(int code){
        this.code = code
    }

    /**
     * @param code the int value associated with a instance of Status
     * @return Status
     */
    static Status from(int code){
        def result
        switch(code){
            case 1 : result = ACTIVE; break
            case 2 : result = DEACTIVE; break
            case 3 : result = PAUSED; break
            default : result = UNKNOWN; break
        }
        result
    }

    /**
     *
     * @param code The string value that may or may not return a valid status
     * @return Status!
     */
    static Status from(String code){
        def result = UNKNOWN
        if(Strings.hasText(code)){
            code = code.toLowerCase()
            switch(code){
                case "active" : result = ACTIVE; break
                case "deactive" : result = DEACTIVE; break
                case "de-active" : result = DEACTIVE; break
                case "paused" : result = PAUSED; break
                default : result = UNKNOWN
            }
        }
        result
    }
}
