package br.com.igorcarvalho.tests.mockito;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;

/**
 * @ExtendWith(MockitoExtension.class) permite que
 * o junit controle a criação de mocks e injections
 * a partir das anotações do mockito
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Mockito Order check")
public class MockitoInOrder {

    /**
     * @Mock cria mocks de objetos simples
     * que geralmente vao ser usados como
     * dependencia de objetos mais complexos
     * que dependam deles
     */
    @Mock
    private FakeRepository repository;

    @Mock
    private FakeEntityFactory factory;

    /**
     * Cria mocks de objetos que tenham
     * dependencias e injeta a dependencias
     * nesses objetos. As dependendencias precisam
     * estar anotados com @Mock
     */
    @InjectMocks
    private FakeService service;

    /**
     * TODO:adicionar explicação
     */
    @Test
    public void inOrderMockitoTest() {
        //given
        final InOrder orrder = inOrder(factory, repository);
        //when
        service.saveRandom();
        //then
        assertAll(
                "Falhou em executar os metodos na ordem correta",
                () -> orrder.verify(factory).getInstance(),
                () -> orrder.verify(repository).save(null),
                () -> orrder.verifyNoMoreInteractions()
        );
    }
}


