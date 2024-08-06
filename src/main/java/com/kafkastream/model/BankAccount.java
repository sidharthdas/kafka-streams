package com.kafkastream.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankAccount {

    public long id;
    public String accountNumber;
    public Date updatedTime;
    public BigDecimal amount;
    public BankTransaction latestBankTransaction;

    public BankAccount process(BankTransaction bankTransaction) {
        //TODO: To be implemented.
        return this;
    }
}
