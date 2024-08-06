package com.kafkastream.processor;

import org.apache.kafka.streams.processor.AbstractProcessor;
import org.springframework.stereotype.Component;

@Component
public class HomeLoanProcessor extends AbstractProcessor<Long, LoanDetail> {
    @Override
    public void process(Long aLong, LoanDetail loanDetail) {
        HomeLoan homeLoan = new HomeLoan();
        homeLoan.setHomeLoanId(loanDetail.getLoanId());
        homeLoan.setHomeLoanCompany(loanDetail.getLoanCompany());
        homeLoan.setHomeLoanAmount(loanDetail.getLoanAmount());
        homeLoan.setHomeLoanRateOfInterest(8D);
        homeLoan.setHomeLoanYear(loanDetail.getLoanYear());

        this.context.forward(aLong, homeLoan);
        this.context.commit();
    }
}
