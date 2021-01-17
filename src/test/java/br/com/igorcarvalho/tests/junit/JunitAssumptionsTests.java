package br.com.igorcarvalho.tests.junit;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.*;

/**
 * Assumptions servem para fazer alguma validação que interrompe ou nao
 * as instruções subsequentes nos testes
 * */
//@SpringBootTest
class JunitAssumptionsTests {

    /**
     * Se a assumpption não for a esperado o teste é ignorado
     * e a execução das intruçoes subsequentes nao sao executadas
     * */
    @Test
    void assumeTrueTest() {
        assumeTrue(false);
        assertEquals(true, false);
    }
    @Test
    void assumeFalseTest() {
        assumeFalse(false);
        assertEquals(true, false);
    }

    /**
     * Se a assumpption for falsa nao executa a expressao lambda mas
     * o teste ainda passa com sucesso
     * */
    @Test
    void assumingThatFalseTest() {
        assumingThat(false, ()-> assertEquals(true, true));
    }

    /**
     * Se a assumpption for true executa a expressao lambda
     * e valida o teste a partir dela
     * */
    @Test
    void assumingThatTrueTest() {
        assumingThat(true, ()-> assertEquals(true, false));
    }

}
