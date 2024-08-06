package com.kafkastream.service;

import com.kafkastream.model.ParsedVoiceCommand;
import com.kafkastream.model.VoiceCommand;

public interface VoiceToTextParserService {

    ParsedVoiceCommand parseVoice(VoiceCommand voiceCommand);
}
