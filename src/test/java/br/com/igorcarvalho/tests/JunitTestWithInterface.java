package br.com.igorcarvalho.tests;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

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
