package br.com.igorcarvalho.tests.examples.tdd.money;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DollarVOTest {

    MoneyVO fiveDollars;

    @BeforeEach
    void setUp() {
        fiveDollars = new MoneyVO(5.0, CurrencyTypeEnum.DOLLAR);
    }

    @Test
    void multiplicationTest() {
        MoneyVO tenDollars = fiveDollars.times(2.0);
        assertEquals(new MoneyVO(10.0, CurrencyTypeEnum.DOLLAR), tenDollars);
    }

    @Test
    void equalityTest() {
        assertAll("equality test",
                () -> assertEquals(fiveDollars, new MoneyVO(5.0, CurrencyTypeEnum.DOLLAR)),
                () -> assertNotEquals(fiveDollars, new MoneyVO(7.0, CurrencyTypeEnum.DOLLAR)),
                () -> assertNotEquals(fiveDollars, new MoneyVO(5.0, CurrencyTypeEnum.FRANC))
        );
    }

    @Test
    void currencyEqualityTest() {
        assertEquals(CurrencyTypeEnum.DOLLAR, fiveDollars.currency());
    }
}
