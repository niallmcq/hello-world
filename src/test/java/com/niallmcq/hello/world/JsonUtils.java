package com.niallmcq.hello.world;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Objects;

public class JsonUtils {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String writeJson(final Object object) throws JsonProcessingException {
        return Objects.nonNull(object) ? OBJECT_MAPPER.writeValueAsString(object) : "{}";
    }
}
