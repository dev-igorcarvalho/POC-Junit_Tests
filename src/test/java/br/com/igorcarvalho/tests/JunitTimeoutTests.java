package br.com.igorcarvalho.tests;

import org.junit.jupiter.api.*;

import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JunitTimeoutTests {

    /**
     * o assertTimeout verifica se a operação excedeu o tempo limite,
     * porém ele aguarda a execução da operação estar completa para verficar
     * a comparação de tempo. Isso pode gerar demora na bateria de testes.
     * */
    @Test
    void timeoutNotExcedeed(){
        assertTimeout(Duration.ofMinutes(2), ()-> {}, "Operação excedeu o tempo limite");
    }

    @Test
    void timeoutExcedeed(){
        assertTimeout(Duration.ofMillis(5), ()-> {Thread.sleep(5000);}, "Operação excedeu o tempo limite");
    }

    /**
     * o assertTimeoutPreemptively verifica se a operação excedeu o tempo limite,
     * porém ele NÃO aguarda a execução da operação estar completa para verficar
     * a comparação de tempo, gerando um teste mais ágil.
     * */
    @Test
    void timeoutPreemptivelyNotExcedeed(){
        assertTimeoutPreemptively(Duration.ofMillis(1000), ()-> {Thread.sleep(500);}, "Operação excedeu o tempo limite");
    }

    @Test
    void timeoutPreemptivelyExcedeed(){
        assertTimeoutPreemptively(Duration.ofMillis(1000), ()-> {Thread.sleep(30000);}, "Operação excedeu o tempo limite");
    }


}
