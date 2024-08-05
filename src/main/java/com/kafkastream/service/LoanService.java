package com.kafkastream.service;

import com.kafkastream.model.LoanDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class LoanService {

    @Autowired
    private KafkaTemplate<String, LoanDetail> kafkaTemplate;

    public void pushToKafkaTopic(LoanDetail loanDetail) {
        kafkaTemplate.send("loanTopic", "1L", loanDetail);
    }
}
