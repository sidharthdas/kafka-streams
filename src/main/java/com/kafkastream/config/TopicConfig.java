package com.kafkastream.config;

import com.kafkastream.model.HomeLoan;
import com.kafkastream.model.LoanDetail;
import com.kafkastream.model.PersonalLoan;
import com.kafkastream.topic.Topic;
import org.apache.kafka.common.serialization.Serdes;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicConfig {

    private String loanTopic;
    private String homeLoanTopic;
    private String personLoanTopic;

    public Topic<Long, LoanDetail> getLoanDetailTopic() {
        return new Topic<>(Serdes.Long(), Serdes.serdeFrom(LoanDetail.class), loanTopic);
    }

    public Topic<Long, HomeLoan> getHomeLoanTopic() {
        return new Topic<>(Serdes.Long(), Serdes.serdeFrom(HomeLoan.class), homeLoanTopic);
    }

    public Topic<Long, PersonalLoan> getPersonalLoanTopic() {
        return new Topic<>(Serdes.Long(), Serdes.serdeFrom(PersonalLoan.class), personLoanTopic);
    }
}
