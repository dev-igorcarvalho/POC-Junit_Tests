package br.com.igorcarvalho.tests.tdd.money;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DollarTest {

    @Test
    void multiplicationTest() {
        Dollar dollar = new Dollar(5.0);
        dollar.times(2.0);
        assertEquals(10, dollar.amount);
    }

    
}
