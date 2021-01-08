package br.com.igorcarvalho.tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
/**
 * @Tag() marca uma classe ou metodo com uma tag
 * e posteriormente podemos usar o nome da tag para filtrar
 * condicionalmente a execução dos testes usando o maven.
 * Classes ou metodos podem conter 1 ou mais @tag()
 * Nomes da tag nao podem ser nulos ou em branco,
 * nao podem conter espaços e nao podem conter
 * caracteres reservados ",()&|!" etc
 * Para filtrar os tests pelas tags usamos no surfire
 * plugin do maven as tags <includeTags>nome-da-tag</includeTags>
 * e <excludeTags>nome-da-tag</excludeTags>
 * */
@Tag("nome-da-tag")
@SpringBootTest
class JunitTaggedTests {


    @Tag("nome1")
    @Tag("tag-extra")
    @Test
    void teste1() {
        assertTrue(true);
    }
    @Tag("nome2")
    @Test
    void teste2() {
        assertTrue(true);
    }
    @Tag("nome3")
    @Test
    void teste3() {
        assertTrue(true);
    }

}
