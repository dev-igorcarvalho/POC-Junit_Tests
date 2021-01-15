package br.com.igorcarvalho.tests;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.math.BigDecimal;
import java.util.stream.Stream;

class JunitCustomArgsProvider2 implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(BigDecimal.valueOf(10)),
                Arguments.of(BigDecimal.valueOf(115)),
                Arguments.of(BigDecimal.valueOf(23)),
                Arguments.of(BigDecimal.valueOf(456)),
                Arguments.of(BigDecimal.valueOf(345))
        );
    }
}
