package com.kafkastream.stream;

import com.kafkastream.config.JsonSerde;
import com.kafkastream.model.LoanDetail;
import com.kafkastream.model.PersonalLoan;
import com.kafkastream.topic.Topic;
import jakarta.annotation.PostConstruct;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanStream {

    @Autowired
    private StreamsBuilder streamsBuilder;

    private String loanTopic = "loanTopic";
    private String homeLoanTopic = "homeLoanTopic";
    final Serde<Long> longSerde = Serdes.Long();

    @Autowired
    private Topic<String, PersonalLoan> getPersonalLoanTopic;



    @PostConstruct
    public void buildStream() {
        KStream<String, PersonalLoan> personalLoanStream = streamsBuilder.stream("loanTopic", Consumed.with(Serdes.String(), new JsonSerde<>(LoanDetail.class)))
                .filter((k, v) -> v.getLoanType().equals("PERSONAL"))
                .mapValues((k, v) -> {
                    PersonalLoan personalLoan = new PersonalLoan();
                    personalLoan.setPersonalLoanId(v.getLoanId());
                    personalLoan.setPersonalLoanCompany(v.getLoanCompany());
                    personalLoan.setPersonalLoanYear(v.getLoanYear());
                    personalLoan.setPersonalLoanAmount(v.getLoanAmount());
                    personalLoan.setPersonalLoanRateOfInterest(12.5D);

                    return personalLoan;
                })
                .peek((k, v) -> System.out.println(v.toString()));

        personalLoanStream.to(getPersonalLoanTopic.getTopicName(), Produced.with(Serdes.String(), new JsonSerde<>(PersonalLoan.class)));

        /*KStream<Long, String> personalLoanStream1 = streamsBuilder
                .stream("loanTopic", Consumed.with(Serdes.Long(), Serdes.String()))
                .filter((k, v) -> v.startsWith("Mess"))
                .mapValues((k, v) -> v.toUpperCase())
                .peek((k, v) -> System.out.println(v));

        personalLoanStream1.to(personalLoanTopic,  Produced.with(Serdes.Long(), Serdes.String()));*/
    }

}
