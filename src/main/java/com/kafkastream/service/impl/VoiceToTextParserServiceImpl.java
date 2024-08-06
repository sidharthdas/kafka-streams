package com.kafkastream.service.impl;

import com.kafkastream.model.ParsedVoiceCommand;
import com.kafkastream.model.VoiceCommand;
import com.kafkastream.service.VoiceToTextParserService;
import org.springframework.stereotype.Service;

@Service
public class VoiceToTextParserServiceImpl implements VoiceToTextParserService {

    @Override
    public ParsedVoiceCommand parseVoice(VoiceCommand voiceCommand) {
        return null;
    }
}
