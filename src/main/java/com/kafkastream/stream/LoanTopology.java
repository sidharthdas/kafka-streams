package com.kafkastream.stream;

import com.kafkastream.model.HomeLoan;
import com.kafkastream.model.LoanDetail;
import com.kafkastream.model.PersonalLoan;
import com.kafkastream.processor.HomeLoanProcessor;
import com.kafkastream.processor.PersonalLoanProcessor;
import com.kafkastream.topic.Topic;
import jakarta.annotation.PostConstruct;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.ssl.DefaultSslBundleRegistry;
import org.springframework.stereotype.Service;

@Service
public class LoanTopology {

    @Autowired
    private StreamsBuilder streamsBuilder;

    @Autowired
    private Topic<String, LoanDetail> loanDetailTopic;
    @Autowired
    private Topic<String, PersonalLoan> personalLoanTopic;
    @Autowired
    private Topic<String, HomeLoan> homeLoanTopic;
    @Autowired
    private KafkaProperties kafkaProperties;


    @PostConstruct
    public void stream() {

        //Topology topology = streamsBuilder.build();

        /*topology.addSource("SOURCE", loanDetailTopic.getKey().deserializer(), loanDetailTopic.getValue().deserializer()
                , loanDetailTopic.getTopicName())
                .addProcessor("PERSONAL", PersonalLoanProcessor::new, "SOURCE")
                .addProcessor("HOME", HomeLoanProcessor::new, "SOURCE")
                .addSink("PERSONAL-SINK", personalLoanTopic.getTopicName(), "PERSONAL")
                .addSink("HOME-SINK", homeLoanTopic.getTopicName(), "HOME");

        KafkaStreams streams = new KafkaStreams(topology, new StreamsConfig(kafkaProperties
                .buildStreamsProperties(new DefaultSslBundleRegistry())));*/

    }
}
