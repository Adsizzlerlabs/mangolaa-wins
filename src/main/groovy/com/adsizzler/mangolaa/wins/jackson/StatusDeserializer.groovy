package com.adsizzler.mangolaa.wins.jackson

import com.adsizzler.mangolaa.wins.domain.enums.Status
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer

/**
 * Created by ankushsharma on 29/01/18.
 */
class StatusDeserializer extends JsonDeserializer<Status> {

    @Override
    Status deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        Status.from(p.text)
    }

}
