package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.MapperFeature;
import tools.jackson.databind.json.JsonMapper;

/**
 * Helper class for serialization and deserialization of configuration objects (POJOs) in JSON.
 */
public final class POJOHelper {

    private static final Logger LOG = LoggerFactory.getLogger(POJOHelper.class);

    private static final JsonMapper MAPPER = JsonMapper.builder().
            findAndAddModules().
            enable(MapperFeature.USE_GETTERS_AS_SETTERS).
            build();

    public static String serialize(final Object object) {
        String result = null;

        try {
            result = MAPPER.writeValueAsString(object);
        } catch (Exception e) {
            LOG.error("During serialization", e);
        }

        return result;
    }

    public static <T extends Object> String serialize(final T object, final TypeReference<T> reference) {
        String result = null;

        try {
            result = MAPPER.writerFor(reference).writeValueAsString(object);
        } catch (Exception e) {
            LOG.error("During serialization", e);
        }

        return result;
    }

    public static <T extends Object> T deserialize(final String serialized, final Class<T> reference) {
        T result = null;

        try {
            result = MAPPER.readValue(serialized, reference);
        } catch (Exception e) {
            LOG.error("During deserialization", e);
        }

        return result;
    }

    public static <T extends Object> T deserialize(final String serialized, final TypeReference<T> reference) {
        T result = null;

        try {
            result = MAPPER.readValue(serialized, reference);
        } catch (Exception e) {
            LOG.error("During deserialization", e);
        }

        return result;
    }

    private POJOHelper() {
    }
}
