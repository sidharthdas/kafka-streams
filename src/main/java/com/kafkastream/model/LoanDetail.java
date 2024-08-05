package com.kafkastream.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LoanDetail {

    private String loanId;
    private String loanCompany;
    private String loanType;
    private Double loanAmount;
    private int loanYear;

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getLoanCompany() {
        return loanCompany;
    }

    public void setLoanCompany(String loanCompany) {
        this.loanCompany = loanCompany;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public int getLoanYear() {
        return loanYear;
    }

    public void setLoanYear(int loanYear) {
        this.loanYear = loanYear;
    }
}
