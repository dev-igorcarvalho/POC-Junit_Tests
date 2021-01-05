package br.com.igorcarvalho.tests.tdd.money;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FrancVOTest {

    FrancVO fiveFrancs;

    @BeforeEach
    void setUp() {
        fiveFrancs = new FrancVO(5.0);
    }


    @Test
    void multiplicationTest() {
        MoneyVO tenFrancs = fiveFrancs.times(2.0);
        assertEquals(new FrancVO(10.0), tenFrancs);
    }

    @Test
    void equalityTest() {
        assertAll("equality test",
                () -> assertEquals(fiveFrancs, new FrancVO(5.0)),
                () -> assertNotEquals(fiveFrancs, new FrancVO(7.0)),
                () -> assertNotEquals(fiveFrancs, new DollarVO(7.0) {
                })
        );
    }
}
