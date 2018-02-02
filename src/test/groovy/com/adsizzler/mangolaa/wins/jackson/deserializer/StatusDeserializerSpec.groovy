package com.adsizzler.mangolaa.wins.jackson.deserializer

import com.adsizzler.mangolaa.wins.BaseSpockSpec
import com.adsizzler.mangolaa.wins.domain.enums.Status
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import groovy.json.JsonBuilder

/**
 * Created by ankushsharma on 01/02/18.
 */
class StatusDeserializerSpec extends BaseSpockSpec {

    private ObjectMapper mapper
    def jsonParser
    def deserializationCtx

    def setup() {
        mapper = new ObjectMapper()
        mapper.enable(SerializationFeature.INDENT_OUTPUT)
        deserializationCtx = mapper.getDeserializationContext()
    }

    def "Testing out various enum values "(){

        given :
            def json = new JsonBuilder([status: status ]).toPrettyString()
            jsonParser = mapper.getFactory().createParser(json)

        when :
            jsonParser.nextToken()
            jsonParser.nextToken()
            jsonParser.nextToken()

            def result = new StatusDeserializer().deserialize(jsonParser,deserializationCtx)

        then :
            result  == expectedResult

        where :
            status                         ||  expectedResult
            'active'                       ||  Status.ACTIVE
            'deactive'                     ||  Status.DEACTIVE
            'random_string'                ||  Status.UNKNOWN
            'ACTIVE'                       ||  Status.ACTIVE
    }


}
