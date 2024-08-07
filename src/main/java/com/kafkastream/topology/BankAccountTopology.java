package com.kafkastream.topology;

import com.kafkastream.config.JsonSerde;
import com.kafkastream.config.StreamsConfiguration;
import com.kafkastream.model.BankAccount;
import com.kafkastream.model.BankTransaction;
import com.kafkastream.model.BankTransactionStatus;
import jakarta.annotation.PostConstruct;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.stereotype.Component;

@Component
public class BankAccountTopology {

    private static final String BANK_TRANSACTIONS = "bank-transaction-topic";
    private static final String BANK_ACCOUNT = "bank-account-topic";
    private static final String BANK_TRANSACTIONS_REJECTED = "bank-transaction-rejected-topic";


    public Topology buildBankAccountTopology() {
        StreamsBuilder streamsBuilder = new StreamsBuilder();

        KStream<Long, BankAccount> bankAccountKStream = streamsBuilder
                .stream(BANK_TRANSACTIONS, Consumed.with(Serdes.Long(),
                        new JsonSerde<>(BankTransaction.class)))
                .groupByKey()
                .aggregate(BankAccount::new,
                        (k, v, aggregate) -> aggregate.process(v),
                        Materialized.with(Serdes.Long(), new JsonSerde<BankAccount>(BankAccount.class))
                ).toStream();

        bankAccountKStream
                .to(BANK_ACCOUNT, Produced.with(Serdes.Long(), new JsonSerde<>(BankAccount.class)));

        bankAccountKStream
                .mapValues((k, v) -> v.getLatestBankTransaction())
                .filter((k, v) -> v.getBankTransactionStatus() == BankTransactionStatus.FAILED)
                .to(BANK_TRANSACTIONS_REJECTED, Produced.with(Serdes.Long(), new JsonSerde<>(BankTransaction.class)));


        return streamsBuilder.build();
    }
}
