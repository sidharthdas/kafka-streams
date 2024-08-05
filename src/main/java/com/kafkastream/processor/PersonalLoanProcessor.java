package com.kafkastream.processor;

import com.kafkastream.model.LoanDetail;
import com.kafkastream.model.PersonalLoan;
import org.apache.kafka.streams.processor.AbstractProcessor;
import org.springframework.stereotype.Service;

@Service
public class PersonalLoanProcessor extends AbstractProcessor<Long, LoanDetail> {
    @Override
    public void process(Long k, LoanDetail v) {

        PersonalLoan personalLoan = new PersonalLoan();
        personalLoan.setPersonalLoanId(v.getLoanId());
        personalLoan.setPersonalLoanCompany(v.getLoanCompany());
        personalLoan.setPersonalLoanYear(v.getLoanYear());
        personalLoan.setPersonalLoanAmount(v.getLoanAmount());
        personalLoan.setPersonalLoanRateOfInterest(12.5D);

        this.context.forward(k, personalLoan);
        this.context.commit();

    }
}
