package br.com.igorcarvalho.tests;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BoilerplateTests {
    /**
     * Qualquer metodo que tenha a anotation @BeforeAll
     * vai ser executado antes de todos os testes
     * */
    @BeforeAll
    static void beforeAllTests() {
    }

    /**
     * Qualquer metodo que tenha a anotation @BeforeEach
     * vai ser executado antes de de cada teste
     * */
    @BeforeEach
    void beforeEachTest() {
    }

    @Test
    void teste1() {
        assertEquals(2, 1, "mensagem a ser exibida no log de quando o teste falha");
    }

    /**
     * @Disabled() faz com q um teste nao seja executado
     * se for colocada sobre o nome da classe faz com que
     * a classe nao seja utilizada no suit de testes
     * */
    @Disabled("Não to afim de rodar esse teste")
    @Test
    void teste2() {
        assertTrue(false);
    }


    /**
     * DisplayName modifica o nome que sera exibido
     * na tela de testes, exibindo a string ao invés
     * do nome do metodo
     * */
    @DisplayName("Nome personalizado")
    @Test
    void teste3() {
        assertTrue(true);
    }

    /**
     * assert all testa todos os cenarios agrupados
     * e tem sucesso apenas se todos os casos forem
     * bem sucedidos
     * */
    @Test
    void groupAssertions() {
        int number = 3;
        assertAll("titulo do grupo",
                () -> assertEquals(number, 1),
                () -> assertEquals(number, 2),
                () -> assertEquals(number, 3)
        );

    }

    @Test
    void exceptionTesting() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    throw new IllegalArgumentException("uma exception");
                });
    }

    /**
     * Qualquer metodo que tenha a anotation @AfterEach
     * vai ser executado depois de de cada teste
     * */
    @AfterEach
    void afterEachTest() {
    }

    /**
     * Qualquer metodo que tenha a anotation @AfterAll
     * vai ser executado depois de todos os testes
     * */
    @AfterAll
    static void afterAllTests() {
    }


}
