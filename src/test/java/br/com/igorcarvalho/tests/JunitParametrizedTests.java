package br.com.igorcarvalho.tests;

import org.assertj.core.util.Strings;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


//@SpringBootTest
class JunitParametrizedTests {

    /**
     * @ValueSource contem uma lista de valores
     * com os seguintes tipos : shorts, bytes,
     * ints, longs, floats, doubles, chars,
     * strings, classes. O tipo selecionado na
     * anotaçao deve ser passado na assinatura
     * do metodo de teste
     * Pode se usar o parametro "name" na anotação
     * para modificar o nome exibido em cada itereção
     */
    @ParameterizedTest()
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
     */
    @ParameterizedTest
    @MethodSource("bigDecimalProvider")
    void singleValuesMethodSourceTest(BigDecimal input) {
        assertNotNull(input);
    }

    /**
     * Nessa caso poderia ter sido passado
     * uma classe qualquer de dominio do software
     * para ser testada, ou ate mesmo mais de uma classes
     * de dominio para serem comparadas uma com a outra
     * igual foi feito no metodo anterior, porém o provedor
     * de valores vem de outra classe que implements ArgumentsProvider
     * isso ajuda a manter o a classe de teste mais limpa
     */
    @ParameterizedTest
    @ArgumentsSource(JunitCustomArgsProvider.class)
    void customClassArgsProviderTest(BigDecimal input) {
        assertNotNull(input);
    }


    /**
     * Tambem é possível ter varias classes personalizadas
     * passando um stream de args para serem testados
     * */
    @ParameterizedTest
    @ArgumentsSources(
            {@ArgumentsSource(JunitCustomArgsProvider.class),
                    @ArgumentsSource(JunitCustomArgsProvider2.class)}
    )
    void multipleCustomClassArgsProviderTest(BigDecimal input) {
        assertNotNull(input);
    }


    /**
     * @CsvSource aceita um csv como parametro para
     * ser testado. É necessário os dados no csv estejam
     * de acordo com o type dos parametros passados na
     * assinatura do teste
     */
    @DisplayName("CSV")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @CsvSource(value = {
            "nome1, 22, 1.67",
            "nome2, 33, 1.55",
            "nome3, 44, 1.78",
            "nome4, , 1.78",
    })
    void csvSourceTest(String nome, int idade, double altura) {
        assertAll("Verficando CSV source",
                () -> assertNotNull(nome),
                () -> assertNotNull(idade),
                () -> assertNotNull(altura)
        );
    }

    /**
     * @CsvSource aceita um arquivos csv como parametro
     * para ser testado. É necessário os dados no csv
     * estejam de acordo com o type dos parametros
     * passados na  assinatura do teste
     * É necessario criar um resources folder na raiz
     * do pacote de testes e dar reload no maven project
     * para o junit achar o resources folder
     * É possivel modificar o delimitador com o parametro "delimiter"
     * É possivel modificar o encode com o parametro "encoding"
     * É possivel modificar separação de linhas/elementos
     * com o parametro "lineSeparator"
     * É possivel especificar que o junit ignore X numeros de linhas
     * geralmente linhas reservadas para headers ou codigos de identificaçao
     * do documento
     */
    @DisplayName("Csv-File")
    @ParameterizedTest(name = "Elemento [{index}] - {arguments}")
    @CsvFileSource(numLinesToSkip = 1, resources = "/inputFile.csv")
    void csvFileSourceTest(String nome, int idade, double altura) {
        assertAll("Verficando CSV source",
                () -> assertNotNull(nome),
                () -> assertNotNull(idade),
                () -> assertNotNull(altura)
        );
    }
}

