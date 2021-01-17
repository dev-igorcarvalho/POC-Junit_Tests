package br.com.igorcarvalho.tests.junit;

import org.junit.jupiter.api.*;

/**
 * @SpringBootTest incia o spring e roda
 * o teste dentro do spring container
 * */
//@SpringBootTest
class JunitTestWithInterface implements TestInterface {

    @Test
    void testWithInterface() {
        System.out.println("Teste executado apos os metodos da interface terem sido executados");
    }
}
