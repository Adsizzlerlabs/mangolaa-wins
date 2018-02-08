package com.adsizzler.mangolaa.wins.jackson.serializer

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider

/**
 * Created by ankushsharma on 02/02/18.
 */
class UUIDSerializer extends JsonSerializer<UUID> {

    @Override
    void serialize(UUID value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeString(value.toString())
    }
}
