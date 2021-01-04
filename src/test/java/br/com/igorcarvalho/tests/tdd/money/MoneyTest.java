package br.com.igorcarvalho.tests.tdd.money;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DollarTest {

    Dollar fiveDollars;

    @BeforeEach
    void setUp() {
         fiveDollars = new Dollar(5.0);
    }

    @Test
    void multiplicationTest() {
        Dollar finalValue = fiveDollars.times(2.0);
        assertEquals(10, finalValue.getAmount());
    }


}
