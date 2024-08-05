package com.kafkastream.config;

import com.kafkastream.model.HomeLoan;
import com.kafkastream.model.LoanDetail;
import com.kafkastream.model.PersonalLoan;
import com.kafkastream.topic.Topic;
import org.apache.kafka.common.serialization.Serdes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicConfig {

    private String loanTopic = "loanTopic";
    private String homeLoanTopic = "homeLoanTopic";
    private String personLoanTopic = "personLoanTopic";

    @Bean
    public Topic<String, LoanDetail> getLoanDetailTopic() {
        return new Topic<>(Serdes.String(), new JsonSerde<>(LoanDetail.class), loanTopic);
    }

    @Bean
    public Topic<String, HomeLoan> getHomeLoanTopic() {
        return new Topic<>(Serdes.String(), new JsonSerde<>(HomeLoan.class), homeLoanTopic);
    }

    @Bean
    public Topic<String, PersonalLoan> getPersonalLoanTopic() {
        return new Topic<>(Serdes.String(), new JsonSerde<>(PersonalLoan.class), personLoanTopic);
    }
}
