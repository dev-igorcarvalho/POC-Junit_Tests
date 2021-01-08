package br.com.igorcarvalho.tests;

import org.junit.jupiter.api.*;

@Tag("Interface")
@DisplayName("Test Interface")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
/**
 * Uma interface de testes pode ser usada para
 * que os testes que a implementam sejam afetados
 * pelas anotations e pelos metodos default
 * Excelente opção quando um grupo de classes de teste
 * utiliza o mesmo set up evitando assim repetição de
 * codigo entre as classes
 * */
public interface TestInterface {
    @BeforeAll
    default void beforeAllTests() {
        System.out.println("Before All vindo da interface");
    }
    @BeforeEach
    default void beforeEachTest() {
        System.out.println("Before Each vindo da interface");
    }
    @AfterAll
    default void afterAllTests() {
        System.out.println("After All vindo da interface");
    }
    @AfterEach
    default void afterEachTest() {
        System.out.println("After Each vindo da interface");
    }
}
