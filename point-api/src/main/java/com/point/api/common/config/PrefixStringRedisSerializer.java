package com.point.api.common.config;

import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.lang.Nullable;

import java.util.Objects;

public class PrefixStringRedisSerializer extends StringRedisSerializer {

    private final String prefix;

    public PrefixStringRedisSerializer(final String prefix) {
        this.prefix = prefix;
    }

    @Override
    public byte[] serialize(@Nullable String string) throws SerializationException {
        return Objects.isNull(string) ? null : super.serialize(prefix + string);
    }
}
