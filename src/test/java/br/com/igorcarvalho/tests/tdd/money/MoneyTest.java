package br.com.igorcarvalho.tests.tdd.money;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DollarVOTest {

    DollarVO fiveDollars;

    @BeforeEach
    void setUp() {
        fiveDollars = new DollarVO(5.0);
    }

    @Test
    void multiplicationTest() {
        DollarVO finalValue = fiveDollars.times(2.0);
        assertEquals(10, finalValue.getAmount());
    }

    @Test
    void equalityTest() {
        assertAll("equality teste",
                () -> assertEquals(fiveDollars, new DollarVO(5.0)),
                () -> assertNotEquals(fiveDollars, new DollarVO(7))
        );
    }
}
