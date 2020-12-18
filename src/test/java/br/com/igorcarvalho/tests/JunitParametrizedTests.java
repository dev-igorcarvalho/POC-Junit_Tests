package br.com.igorcarvalho.tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class JunitParametrizedTests {

    /**
     * @ValueSource contem uma lista de valores
     * com os seguintes tipos : shorts, bytes,
     * ints, longs, floats, doubles, chars,
     * strings, classes. O tipo selecionado na
     * anotaçao deve ser passado na assinatura
     * do metodo de teste
     */
    @ParameterizedTest
    @ValueSource(strings = {"string1", "string2", "string3"})
    void valueSourceParametrizedTest(String args) {
        assertTrue(args.contains("string"));
    }

    public enum RandomTypeEnum {
        TESTE1, TESTE2, TESTE3, TESTE4, TESTE5, TESTE
    }

    /**
     * @EnumSource() recebe um enum.class
     * e roda o teste 1 vez para cada constante
     * do enum
     */
    @ParameterizedTest
    @EnumSource(value = RandomTypeEnum.class)
    void enumSourceParametrizedTest(RandomTypeEnum args) {
        assertNotNull(args);
    }

    /**
     * mode = EnumSource.Mode.INCLUDE, testa apenas
     * as constantes do enum q foram selecionadas no
     * names = {"TESTE1","TESTE2"}
     * */
    @ParameterizedTest
    @EnumSource(value = RandomTypeEnum.class,
            mode = EnumSource.Mode.INCLUDE,
            names = {"TESTE1","TESTE2"})
    void enumSourceWithModeIncludeParametrizedTest(RandomTypeEnum args) {
        assertNotNull(args);
    }

    /**
     * mode = EnumSource.Mode.EXCLUDE, exclui do teste
     * as constantes do enum q foram selecionadas no
     * names = {"TESTE1","TESTE2"}
     * */
    @ParameterizedTest
    @EnumSource(value = RandomTypeEnum.class,
            mode = EnumSource.Mode.EXCLUDE,
            names = {"TESTE1"})
    void enumSourceWithModeExcludeParametrizedTest(RandomTypeEnum args) {
        assertNotNull(args);
    }

    /**
     * mode = EnumSource.Mode.MATCH_ALL, testa apenas
     * as constantes do enum q foram selecionadas no
     * names = {"TESTE1","TESTE2"} porem aceita ReGex
     * */
    @ParameterizedTest
    @EnumSource(value = RandomTypeEnum.class,
            mode = EnumSource.Mode.MATCH_ALL,
            names = {"TESTE"})
    void enumSourceWithModeMatchAllParametrizedTest(RandomTypeEnum args) {
        assertNotNull(args);
    }

    /**
     * mode = EnumSource.Mode.MATCH_ALL, testa quaisquer
     * constantes do enum q foram selecionadas no
     * names = {"TESTE1","TESTE2"} porem aceita ReGex
     * */
    @ParameterizedTest
    @EnumSource(value = RandomTypeEnum.class,
            mode = EnumSource.Mode.MATCH_ANY,
            names = {"TESTE"})
    void enumSourceWithModeMatchAnyetrizedTest(RandomTypeEnum args) {
        assertNotNull(args);
    }

}

