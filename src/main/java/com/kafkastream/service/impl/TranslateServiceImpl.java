package com.kafkastream.service.impl;

import com.kafkastream.model.ParsedVoiceCommand;
import com.kafkastream.service.TranslateService;
import org.springframework.stereotype.Service;

@Service
public class TranslateServiceImpl implements TranslateService {

    @Override
    public ParsedVoiceCommand toEnglish(ParsedVoiceCommand current) {
        return null;
    }
}
