package br.com.igorcarvalho.tests.tdd.money;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DollarTest {

    @Test
    void multiplicationTest() {
        Dollar initialValue = new Dollar(5.0);
        Dollar finalValue = initialValue.times(2.0);
        assertEquals(10, finalValue.getAmount());
    }


}
