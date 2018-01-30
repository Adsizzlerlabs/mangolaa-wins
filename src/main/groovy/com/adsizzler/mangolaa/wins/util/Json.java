package com.adsizzler.mangolaa.wins.util;


import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Created by Ankush on 17/07/17.
 */
public class Json {

    //Reasons for static initialization, check out this link:
    //https://stackoverflow.com/questions/18611565/how-do-i-correctly-reuse-jackson-objectmapper
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T toObject(final String json, final Class<T> clazz) throws Exception {
        Assert.notNull(clazz, "clazz cannot be null");
        return objectMapper
                .readValue(json, clazz);
    }

    public static String encode(final Object object) throws Exception {
        Assert.notNull(object, "object cannot be null");
        return objectMapper
                .writer()
                .writeValueAsString(object);
    }

    public static String toPrettyJson(final Object object) throws Exception {
        Assert.notNull(object, "object cannot be null");
        return objectMapper
                .writer()
                .withDefaultPrettyPrinter()
                .writeValueAsString(object);
    }


}
