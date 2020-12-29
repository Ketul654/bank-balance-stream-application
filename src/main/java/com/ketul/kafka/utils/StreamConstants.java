package com.ketul.kafka.utils;

public class StreamConstants {

    public static final String BANK_BALANCE_APPLICATION_ID = "bank-balance-stream-application";
    public static final String BOOTSTRAP_SERVERS = "localhost:9092, localhost:9093, localhost:9094";
    public static final String AUTO_OFFSET_RESET_EARLIEST = "earliest";
    public static final String BANK_TRANSACTIONS_TOPIC = "bank-transactions";
    public static final String BANK_BALANCES_TOPIC = "bank-balances";
    public static final String ALL_ACKS = "all";
    public static final String RETRIES = "3";
    public static final boolean ENABLE_IDEMPOTENCE = true;
    public static final int LINGER_MS = 1;

    private StreamConstants(){}
}
