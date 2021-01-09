package br.com.igorcarvalho.tests;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.boot.test.context.SpringBootTest;

/**
 * @SpringBootTest incia o spring e roda
 * o teste dentro do spring container
 * */
//@SpringBootTest
@DisplayName("Nome personalizado da classe")
class JunitBasics {
    /**
     * Qualquer metodo que tenha a anotation @BeforeAll
     * vai ser executado antes de todos os testes
     */
    @BeforeAll
    static void beforeAllTests() {
    }

    /**
     * Qualquer metodo que tenha a anotation @BeforeEach
     * vai ser executado antes de de cada teste
     */
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
     */
    @Disabled("Não to afim de rodar esse teste")
    @Test
    void teste2() {
        assertTrue(false);
    }


    /**
     * DisplayName modifica o nome que sera exibido
     * na tela de testes, exibindo a string ao invés
     * do nome do metodo.
     * Tambem pode ser usado no nome da classe
     */
    @DisplayName("Nome personalizado")
    @Test
    void teste3() {
        assertTrue(true);
    }

    /**
     * assert all testa todos os cenarios agrupados
     * e tem sucesso apenas se todos os casos forem
     * bem sucedidos
     */
    @Test
    void groupAssertions() {
        int number = 3;
        assertAll("titulo do grupo",
                () -> assertEquals(number, 1),
                () -> assertEquals(number, 2),
                () -> assertEquals(number, 3)
        );
    }

    /**
     * É possível agrupar varios grupos
     * para testar objetos mais complexos
     * e que tenham dependencias
     */
    @Test
    void groupDependentAssertions() {
        int number = 3;
        assertAll("titulo do grupo",
                () -> assertAll("subgrupo 1",
                        () -> assertEquals(number, 1),
                        () -> assertEquals(number, 2)
                ),
                () -> assertAll(
                        "subgrupo 2",
                        () -> assertEquals(number, 3),
                        () -> assertEquals(number, 7)
                )
        );
    }

    /**
     * @RepeatedTest Faz com q um teste seja reptido o numero de vezes solicitadas no value
     * e nas repetiçoes exibe o texto contido no @RepeatedTest(name="")
     */
    @DisplayName("Titulo principal")
    @RepeatedTest(value = 5,
            name = "Titulo da repetição 5x : repetição {currentRepetition} / {totalRepetitions}")
    void repeatedTest() {
        System.out.println("Rodando repetição do teste");
    }


    /**
     * Junit permite passar dados do teste
     * via DI para dentro do teste
     * Tambem pode ser usado nos metodos before e after
     * porem o RepetitionInfo so funciona para repeated tests
     * ou em before e after de classes que so tenham @RepeatedTest
     * */
    @Tag("TestDI")
    @RepeatedTest(value = 2,
            name = "Teste passando dados por DI")
    void repeatedTestWithDi(TestInfo info, RepetitionInfo rep, TestReporter report) {
        System.out.println(info.getDisplayName());
        System.out.println(info.getTags());
        System.out.println(info.getTestClass());
        System.out.println(info.getTestMethod());
        System.out.println(rep.getCurrentRepetition());
        System.out.println(rep.getTotalRepetitions());
//        report.publishEntry("Wtf");
        report.publishEntry("Chave","Valor");
    }

    /**
     * Testa se a exception esperada foi
     * realmente lançada
     * */
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
     */
    @AfterEach
    void afterEachTest() {
    }

    /**
     * Qualquer metodo que tenha a anotation @AfterAll
     * vai ser executado depois de todos os testes
     */
    @AfterAll
    static void afterAllTests() {
    }

}
