package com.kafkastream.topology;

import com.kafkastream.config.StreamsConfiguration;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;

public class VoiceCommandParserTopology {

    public void runKafkaStream() {
        Topology topology = voiceCommandParserTopology();
        KafkaStreams kafkaStreams = new KafkaStreams(topology, new StreamsConfiguration().streamsConfig());

        kafkaStreams.start();
        //When we shut-down the application, kafka stream will shutdown automatically
        Runtime.getRuntime().addShutdownHook(new Thread(kafkaStreams::close));
    }

    private Topology voiceCommandParserTopology() {
        StreamsBuilder streamsBuilder = new StreamsBuilder();
       /* Scenario:
                * 1. Once you get the data in the stream, filter audio size > O parse the speech to text (mapValues)
                * 2. Once it is parsed, split the data into 2 category i.e. recognized & unrecognized based un threshold
                * 3. Unrecognized will go to unrecognized topic
                * 4. recognized : split it to english or not-english language
                * 5. Not-english will be translated to english
                * 6. merge english & translated to english streams
                * 7. put the data to Kstream*/


        return streamsBuilder.build();
    }
}
