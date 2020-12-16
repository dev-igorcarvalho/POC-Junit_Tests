package br.com.igorcarvalho.tests;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Nested
 * Testes aninhados trazem a capcidade de relacionar e ordenar
 * um grupo de testes que sejam interdependentes
 * Os @BeforeEach e @AfterEach das classes mais externas sao executados
 * também nos testes das classes mais internas.
 * As classes internas não podem ter metodos estaticos
 * */
@SpringBootTest
@DisplayName("Classe Pai")
class JunitNestedTests {

    @BeforeAll
    static void initClass() {
        System.out.println("Iniciando classe pai");
    }

    @AfterAll
    static void killClass() {
        System.out.println("Destruindo classe pai");
    }

    @BeforeEach
    void initTest() {
        System.out.println("Inciando setup de test");
    }

    @AfterEach
    void killTest() {
        System.out.println("Destruindo setup de test");
    }

    @Test
    @DisplayName("Classe Pai : teste 1")
    void teste1() {
        System.out.println("Inciando test1");
    }

    @Test
    @DisplayName("Classe Pai : teste 2")
    void teste2() {
        System.out.println("Inciando test2");
    }

    @Nested
    @DisplayName("Classe filha")
    class firstInnerClass {
        @BeforeEach
        void initTest() {
            System.out.println("Inciando setup de teste Classe Filha");
        }
        @Test
        @DisplayName("Classe Filha : teste 1")
        void teste1() {
            System.out.println("Inciando test1");
        }

        @Test
        @DisplayName("Classe Filha : teste 2")
        void teste2() {
            System.out.println("Inciando test2");
        }

        @Nested
        @DisplayName("Classe Neta")
        class secondInnerClass {
            @BeforeEach
            void initTest() {
                System.out.println("Inciando setup de teste Classe Neta");
            }
            @Test
            @DisplayName("Classe Neta : teste 1")
            void teste1() {
                System.out.println("Inciando test1");
            }

            @Test
            @DisplayName("Classe Neta : teste 2")
            void teste2() {
                System.out.println("Inciando test2");
            }
        }
    }


}
