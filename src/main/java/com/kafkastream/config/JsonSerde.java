package com.kafkastream.config;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serde;

public class JsonSerde<T> extends Serdes.WrapperSerde<T> {
    public JsonSerde(Class<T> targetClass) {
        super(new JsonSerializer<>(), new JsonDeserializer<>(targetClass));
    }
}
