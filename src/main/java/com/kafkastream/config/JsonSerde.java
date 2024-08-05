package com.kafkastream.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import java.io.IOException;

public class JsonSerde<T> implements Serde<T>{

    private final Class<T> type;

    public JsonSerde(Class<T> type) {
        this.type = type;
    }

    @Override
    public void close() {
        Serde.super.close();
    }

    @Override
    public Serializer<T> serializer() {
        return (topic, data) -> {
            try {
                return new ObjectMapper().writeValueAsBytes(data);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        };
    }

    @Override
    public Deserializer<T> deserializer() {

        return (topic, data)-> {
            try {
                return new ObjectMapper().readValue(data, type);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }
}
