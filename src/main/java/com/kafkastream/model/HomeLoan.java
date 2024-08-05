package com.kafkastream.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class HomeLoan {

    private String homeLoanId;
    private String homeLoanCompany;
    private Double homeLoanAmount;
    private Double homeLoanRateOfInterest;
    private int homeLoanYear;

    public String getHomeLoanId() {
        return homeLoanId;
    }

    public void setHomeLoanId(String homeLoanId) {
        this.homeLoanId = homeLoanId;
    }

    public String getHomeLoanCompany() {
        return homeLoanCompany;
    }

    public void setHomeLoanCompany(String homeLoanCompany) {
        this.homeLoanCompany = homeLoanCompany;
    }

    public Double getHomeLoanAmount() {
        return homeLoanAmount;
    }

    public void setHomeLoanAmount(Double homeLoanAmount) {
        this.homeLoanAmount = homeLoanAmount;
    }

    public Double getHomeLoanRateOfInterest() {
        return homeLoanRateOfInterest;
    }

    public void setHomeLoanRateOfInterest(Double homeLoanRateOfInterest) {
        this.homeLoanRateOfInterest = homeLoanRateOfInterest;
    }

    public int getHomeLoanYear() {
        return homeLoanYear;
    }

    public void setHomeLoanYear(int homeLoanYear) {
        this.homeLoanYear = homeLoanYear;
    }
}
