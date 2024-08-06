package com.kafkastream.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import java.io.IOException;

public class JsonSerde<T> implements Serde<T>{

    private final Class<T> type;
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public JsonSerde(Class<T> type) {
        this.type = type;
    }

    @Override
    public void close() {
        Serde.super.close();
    }

    @Override
    public Serializer<T> serializer() {
        return this::serialize;

    }

    @Override
    public Deserializer<T> deserializer() {
        return this::deserialize;
    }

    @SneakyThrows
    private byte[] serialize(String topic, T data) {
        try {
            return OBJECT_MAPPER.writeValueAsBytes(data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private T deserialize(String topic, byte[] data) {
        try {
            return OBJECT_MAPPER.readValue(data, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
