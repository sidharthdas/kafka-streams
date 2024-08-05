package com.kafkastream.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PersonalLoan {

    private String personalLoanId;
    private String personalLoanCompany;
    private Double personalLoanAmount;
    private Double personalLoanRateOfInterest;
    private int personalLoanYear;

    public String getPersonalLoanId() {
        return personalLoanId;
    }

    public void setPersonalLoanId(String personalLoanId) {
        this.personalLoanId = personalLoanId;
    }

    public String getPersonalLoanCompany() {
        return personalLoanCompany;
    }

    public void setPersonalLoanCompany(String personalLoanCompany) {
        this.personalLoanCompany = personalLoanCompany;
    }

    public Double getPersonalLoanAmount() {
        return personalLoanAmount;
    }

    public void setPersonalLoanAmount(Double personalLoanAmount) {
        this.personalLoanAmount = personalLoanAmount;
    }

    public Double getPersonalLoanRateOfInterest() {
        return personalLoanRateOfInterest;
    }

    public void setPersonalLoanRateOfInterest(Double personalLoanRateOfInterest) {
        this.personalLoanRateOfInterest = personalLoanRateOfInterest;
    }

    public int getPersonalLoanYear() {
        return personalLoanYear;
    }

    public void setPersonalLoanYear(int personalLoanYear) {
        this.personalLoanYear = personalLoanYear;
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
