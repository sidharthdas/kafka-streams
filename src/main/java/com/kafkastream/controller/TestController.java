package com.kafkastream.controller;

import com.kafkastream.model.VoiceCommand;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/")
public class TestController {

    @Autowired
    public KafkaStreams kafkaStreams;

    @Autowired
    public KTable<String, VoiceCommand> kafkaTable;

    @GetMapping
    public VoiceCommand getCommand(@RequestParam("id") String id) {
       // kafkaTable.

        return null;
    }
}
