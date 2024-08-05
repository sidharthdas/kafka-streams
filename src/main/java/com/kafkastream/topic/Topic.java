package com.kafkastream.topic;

import com.kafkastream.config.JsonSerde;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

import java.util.Collections;

public class Topic<K, V> {

    private final Serde<K> key;
    private final JsonSerde<V> value;
    private final String topicName;

    public Topic(Serde<K> key, JsonSerde<V> value, String topicName) {
        this.key = key;
        this.value = value;
        this.topicName = topicName;
    }

    public Serde<K> getKey() {
        return key;
    }

    public JsonSerde<V> getValue() {
        return value;
    }

    public String getTopicName() {
        return topicName;
    }
}
