package com.ketul.kafka.serde;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ketul.kafka.message.BankTransaction;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import java.time.Instant;
import java.util.Map;

public class BankTransactionSerdeTest {

    private ObjectMapper mapper = new ObjectMapper();
    private BankTransactionSerializer serializer = new BankTransactionSerializer(mapper);
    private BankTransactionDeserializer deserializer = new BankTransactionDeserializer(mapper);
    private Map config = Mockito.mock(Map.class);

    @Before
    public void setUp() throws Exception {
        serializer.configure(config, true);
        deserializer.configure(config, true);
    }

    @Test
    public void testValidBankBalance(){
        Instant now = Instant.now();
        String topic = "topic-0";
        byte[] bytes = serializer.serialize(topic, new BankTransaction("ketul", 100f , now));
        BankTransaction transaction = deserializer.deserialize(topic, bytes);
        Assert.assertEquals(transaction.getTime(), now);
        Assert.assertTrue(transaction.getAmount() ==100f);
        Assert.assertTrue(transaction.getName().equals("ketul"));
    }

    @After
    public void tearDown() throws Exception {
        serializer.close();
        deserializer.close();
    }
}