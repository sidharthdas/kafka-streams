package com.kafkastream.service;

import com.kafkastream.model.VoiceCommand;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


@Component
public class VoiceCommandProducer {

    @Autowired
    private KafkaTemplate<String, VoiceCommand> kafkaTemplate;

    private String id = "1";
    private byte[] audio;
    private String audioCodec = "1234";
    private String language = "en-US";
    private int i = 0;


    @Async
    //@PostConstruct
    public void init() throws InterruptedException {

        while(true) {
            Thread.sleep(10000);
            VoiceCommand voiceCommand = new VoiceCommand();
            voiceCommand.setId(id + (++i));
            voiceCommand.setAudio(new byte[20]);
            voiceCommand.setAudioCodec("abc");
            voiceCommand.setLanguage(language);

            kafkaTemplate.send("voice-command-topic", voiceCommand);
        }

    }
}
