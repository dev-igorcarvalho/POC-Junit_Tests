package br.com.igorcarvalho.tests;

import org.assertj.core.util.Strings;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


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
     */
    @ParameterizedTest
    @EnumSource(value = RandomTypeEnum.class,
            mode = EnumSource.Mode.INCLUDE,
            names = {"TESTE1", "TESTE2"})
    void enumSourceWithModeIncludeParametrizedTest(RandomTypeEnum args) {
        assertNotNull(args);
    }

    /**
     * mode = EnumSource.Mode.EXCLUDE, exclui do teste
     * as constantes do enum q foram selecionadas no
     * names = {"TESTE1","TESTE2"}
     */
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
     */
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
     */
    @ParameterizedTest
    @EnumSource(value = RandomTypeEnum.class,
            mode = EnumSource.Mode.MATCH_ANY,
            names = {"TESTE"})
    void enumSourceWithModeMatchAnyetrizedTest(RandomTypeEnum args) {
        assertNotNull(args);
    }


    private static Stream<Arguments> stringAndBooleanProvider() {
        return Stream.of(
                Arguments.of(null, true),
                Arguments.of("", true),
                Arguments.of("  ", true),
                Arguments.of("not blank", false)
        );
    }

    /**
     * @MethodSource("provideStringsForIsBlank") recebe um string com msm nome do metdodo estatico que
     * retorna uma Stream<Arguments> os quais devem ser passados
     * na assinatura do metodo de teste
     * O teste vai rodar uma vez para cada elemento na stream.
     */
    @ParameterizedTest
    @MethodSource("stringAndBooleanProvider")
    void multipleValuesMethodSourceTest(String input, boolean expected) {
        assertEquals(expected, Strings.isNullOrEmpty(input));
    }

    private static Stream<Arguments> bigDecimalProvider() {
        return Stream.of(
                Arguments.of(BigDecimal.valueOf(10)),
                Arguments.of(BigDecimal.valueOf(115)),
                Arguments.of(BigDecimal.valueOf(23)),
                Arguments.of(BigDecimal.valueOf(456)),
                Arguments.of(BigDecimal.valueOf(345))
        );
    }

    /**
     * Nessa caso poderia ter sido passado
     * uma classe qualquer de dominio do software
     * para ser testada, ou ate mesmo mais de uma classes
     * de dominio para serem comparadas uma com a outra
     * igual foi feito no metodo anterior
     * */
    @ParameterizedTest
    @MethodSource("bigDecimalProvider")
    void singleValuesMethodSourceTest(BigDecimal input) {
        assertNotNull(input);
    }


}

