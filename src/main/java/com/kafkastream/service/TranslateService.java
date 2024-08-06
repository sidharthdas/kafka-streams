package com.kafkastream.service;

import com.kafkastream.model.ParsedVoiceCommand;

public interface TranslateService {

    ParsedVoiceCommand toEnglish(ParsedVoiceCommand current);
}
