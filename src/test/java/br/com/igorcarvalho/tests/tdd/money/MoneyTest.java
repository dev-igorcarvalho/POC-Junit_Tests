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
        DollarVO tenDollars = fiveDollars.times(2.0);
        assertEquals(new DollarVO(10.0), tenDollars);
    }

    @Test
    void equalityTest() {
        assertAll("equality teste",
                () -> assertEquals(fiveDollars, new DollarVO(5.0)),
                () -> assertNotEquals(fiveDollars, new DollarVO(7))
        );
    }
}
