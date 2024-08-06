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
public class BankTransaction {

    public long id;
    public String accountNumber;
    public Date updatedTime;
    public BigDecimal amount;
    public TransactionStatus transactionStatus;
    public BankTransactionStatus bankTransactionStatus;

}
