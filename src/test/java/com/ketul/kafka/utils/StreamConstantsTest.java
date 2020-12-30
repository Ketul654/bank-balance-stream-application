package com.ketul.kafka.utils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StreamConstantsTest {

    @Before
    public void setUp() {
    }

    @Test
    public void testConstants(){
        Assert.assertEquals(StreamConstants.BANK_TRANSACTIONS_TOPIC, "bank-transactions");
        Assert.assertEquals(StreamConstants.BANK_BALANCES_TOPIC, "bank-balances");
        Assert.assertTrue(StreamConstants.ENABLE_IDEMPOTENCE);
    }

    @After
    public void tearDown() {
    }
}