package br.com.igorcarvalho.tests.examples.mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * @ExtendWith(MockitoExtension.class) permite que
 * o junit controle a criação de mocks e injections
 * a partir das anotações do mockito
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Mockito Anwser")
public class MockitoAnswer {

    /**
     * @Mock cria mocks de objetos simples
     * que geralmente vao ser usados como
     * dependencia de objetos mais complexos
     * que dependam deles
     */
    @Mock
    private FakeRepository repository;

    @BeforeEach
    void setUp() {
        /**
         * Mockito Answer é usado quando precisamos
         * criar um comportamento de resposta mais
         * complexo que o normal, executando ações
         * diferentes para cenarios diferentes a
         * serem testados na classe
         * */
        when(this.repository.findById(anyLong())).thenAnswer(invocationOnMock -> {
            final Long argument = invocationOnMock.getArgument(0);
            if (argument <= 0) {
                throw new IllegalArgumentException("o id fornecido não existe");
            }
            if (!argument.equals(7L)) {
                return null;
            }
            if (argument.equals(7l)) {
                return new FakeEntity(7L, "retorno via mockito answer");
            }
            throw new RuntimeException("o id fornecido não existe");
        });
    }


    @Test
    public void entityFoundTest() {
        //given
        final ArgumentCaptor<Long> captor =
                ArgumentCaptor.forClass(Long.class);
        //when
        final FakeEntity foundEntity = this.repository.findById(7L);
        //then
        assertAll("Falhou em achar a entidade",
                () -> verify(repository).findById(captor.capture()),
                () -> assertEquals(Long.valueOf(7), captor.getValue()),
                () -> assertNotNull(foundEntity),
                () -> assertEquals(Long.valueOf(7), foundEntity.getId()),
                () -> assertEquals("retorno via mockito answer", foundEntity.getNome()));

    }

    @Test
    public void entityNotFoundTest() {
        //given
        final ArgumentCaptor<Long> captor =
                ArgumentCaptor.forClass(Long.class);
        //when
        final FakeEntity foundEntity = this.repository.findById(22L);
        //then
        assertAll("Falhou em achar a entidade",
                () -> verify(repository).findById(captor.capture()),
                () -> assertNull(foundEntity)
        );
    }

    @Test
    public void expectionThrownTest() {
        //given
        //when
        //then
        assertThrows(IllegalArgumentException.class,
                () -> {
                    this.repository.findById(0L);
                });
    }
}


