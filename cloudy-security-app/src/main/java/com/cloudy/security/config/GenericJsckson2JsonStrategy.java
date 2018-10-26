package com.cloudy.security.config;

import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.security.oauth2.provider.token.store.redis.StandardStringSerializationStrategy;

/**
 * Created by ljy_cloudy on 2018/10/23.
 */
public class GenericJsckson2JsonStrategy extends StandardStringSerializationStrategy {
    private static final GenericJackson2JsonRedisSerializer GENERIC_JACKSON_2_JSON_REDIS_SERIALIZER = new GenericJackson2JsonRedisSerializer();
    @Override
    protected <T> T deserializeInternal(byte[] bytes, Class<T> clazz) {
        return (T) GENERIC_JACKSON_2_JSON_REDIS_SERIALIZER.deserialize(bytes);
    }

    @Override
    protected byte[] serializeInternal(Object object) {
        return GENERIC_JACKSON_2_JSON_REDIS_SERIALIZER.serialize(object);
    }
}
