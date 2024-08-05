package com.kafkastream.topic;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

public class Topic<K, V> {

    private final Serde<K> key;
    private final Serde<V> value;
    private final String topicName;

    public Topic(Serde<K> key, Serde<V> value, String topicName) {
        this.key = key;
        this.value = value;
        this.topicName = topicName;
    }

    public Serde<K> getKey() {
        return key;
    }

    public Serde<V> getValue() {
        return value;
    }

    public String getTopicName() {
        return topicName;
    }
}
