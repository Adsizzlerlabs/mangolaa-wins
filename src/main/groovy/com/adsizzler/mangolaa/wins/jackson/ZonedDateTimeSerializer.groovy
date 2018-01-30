package com.adsizzler.mangolaa.wins.jackson

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

/**
 * Created by ankushsharma on 29/01/18.
 */
class ZonedDateTimeSerializer extends JsonSerializer<ZonedDateTime>{

    @Override
    void serialize(ZonedDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        if(value){
            gen.writeString(DateTimeFormatter.ISO_ZONED_DATE_TIME.format(value))
        }
    }

}
