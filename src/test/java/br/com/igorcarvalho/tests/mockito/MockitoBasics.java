package br.com.igorcarvalho.tests.mockito;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @ExtendWith(MockitoExtension.class) permite que
 * o junit controle a criação de mocks e injections
 * a partir das anotações do mockito
 * */
@ExtendWith(MockitoExtension.class)
@DisplayName("Mockito tests")
public class MockitoBasics {

    /**
     * @Mock cria mocks de objetos simples
     * que geralmente vai ser usados como
     * dependencia de objetos mais complexos
     * que dependam deles
     * */
    @Mock
    Map<String, String> map;

    /**
     * Cria mocks de objetos que tenham
     * dependencias e injeta a dependencias
     * nesses objetos. As dependendencias precisam
     * estar anotados com @Mock
     * */
    @InjectMocks
    Service service;

    /**
     * Teste apenas para verificar se o mockito esta
     * criando os mocks e injetando as dependencias
     * corretamente. Se o mockito nao tivesse criado
     * o service o teste iria dar nullPoointer. Se o
     * mockito nao tivesse criado a dependencia do service
     * o teste iria falhar pois ela seria nula
     * */
    @Test
    void creatingMocksTest() {
        final Map<Long, String> repository = this.service.getRepository();
        assertNotNull(repository, "Falhou na criação dos mocks");
    }
}
