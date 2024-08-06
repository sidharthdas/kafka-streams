package com.kafkastream.topology;

import com.kafkastream.config.JsonSerde;
import com.kafkastream.config.StreamsConfiguration;
import com.kafkastream.model.ParsedVoiceCommand;
import com.kafkastream.model.VoiceCommand;
import com.kafkastream.service.TranslateService;
import com.kafkastream.service.VoiceCommandProducer;
import com.kafkastream.service.VoiceToTextParserService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.*;
import org.springframework.stereotype.Component;

import java.util.Map;

@AllArgsConstructor
@Component
public class VoiceCommandParserTopology {

    private static final String RECOGNISED_VOICE = "recognized-voice-topic";
    private static final String UNRECOGNISED_VOICE = "unrecognized-voice-topic";
    private static final String VOICE_COMMAND = "voice-command-topic";
    private final VoiceToTextParserService voiceToTextParserService;
    private final TranslateService translateService;
    private static final Double THRESHOLD = 0.8;
    private final VoiceCommandProducer producer;

    @PostConstruct
    public void runKafkaStream() throws InterruptedException {
        producer.init();
        Topology topology = voiceCommandParserTopology();
        KafkaStreams kafkaStreams = new KafkaStreams(topology, new StreamsConfiguration().streamsConfig());

        kafkaStreams.start();
        //When we shut-down the application, kafka stream will shutdown automatically
        //Runtime.getRuntime().addShutdownHook(new Thread(kafkaStreams::close));
    }

    public Topology voiceCommandParserTopology() {
        StreamsBuilder streamsBuilder = new StreamsBuilder();
       /* Scenario:
                * 1. Once you get the data in the stream, filter audio size > O parse the speech to text (mapValues)
                * 2. Once it is parsed, split the data into 2 category i.e. recognized & unrecognized based un threshold
                * 3. Unrecognized will go to unrecognized topic
                * 4. recognized : split it to english or not-english language
                * 5. Not-english will be translated to english
                * 6. merge english & translated to english streams
                * 7. put the data to Kstream*/

        Map<String, KStream<String, ParsedVoiceCommand>> branches = streamsBuilder.stream(VOICE_COMMAND,
                        Consumed.with(Serdes.String(), new JsonSerde<>(VoiceCommand.class)))
                .filter((k, v) -> v.getAudio().length > 10)
                .mapValues((k, v) -> voiceToTextParserService.parseVoice(v))
                .split(Named.as("branches-"))
                .branch((k, v) -> v.getProbability() > THRESHOLD, Branched.as("recognized"))
                .defaultBranch(Branched.as("un-recognized"));

        System.out.println("Sidharth"+branches);
        branches.get("branches-un-recognized")
                .to(UNRECOGNISED_VOICE, Produced.with(Serdes.String(), new JsonSerde<>(ParsedVoiceCommand.class)));

        Map<String, KStream<String, ParsedVoiceCommand>> languageBranches = branches.get("branches-recognized")
                .split(Named.as("language-"))
                .branch((k, v) -> v.getLanguage().startsWith("en-"), Branched.as("english"))
                .defaultBranch(Branched.as("non-english"));

        languageBranches.get("language-non-english")
                .mapValues((k, v) -> translateService.toEnglish(v))
                .merge(languageBranches.get("language-english"))
                .to(RECOGNISED_VOICE, Produced.with(Serdes.String(), new JsonSerde<>(ParsedVoiceCommand.class)));

        return streamsBuilder.build();
    }
}
