package br.com.igorcarvalho.tests.mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

/**
 * @ExtendWith(MockitoExtension.class) permite que
 * o junit controle a criação de mocks e injections
 * a partir das anotações do mockito
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Mockito basics")
public class MockitoBasics {

    /**
     * @Mock cria mocks de objetos simples
     * que geralmente vao ser usados como
     * dependencia de objetos mais complexos
     * que dependam deles
     */
    @Mock
    private FakeRepository repository;

    /**
     * Cria mocks de objetos que tenham
     * dependencias e injeta a dependencias
     * nesses objetos. As dependendencias precisam
     * estar anotados com @Mock
     */
    @InjectMocks
    private FakeService service;

    /**
     * Teste apenas para verificar se o mockito esta
     * criando os mocks e injetando as dependencias
     * corretamente. Se o mockito nao tivesse criado
     * o Service o teste iria dar nullPointer. Se o
     * mockito nao tivesse criado a dependencia do service
     * o teste iria falhar pois ela seria nula
     */
    @Test
    void creatingMocksTest() {
        final FakeRepository repository = this.service.getRepository();
        assertNotNull(repository, "Falhou na criação dos mocks");
    }


    /**
     * Verifica se o metodo sob teste
     * foi chamado apenas 1 vez
     */
    @Test
    void verifyOnceTest() {
        this.repository.findById(1l);
        verify(repository).findById(1l);
    }

    /**
     * Verifica se o metodo sob teste
     * foi chamado exatamente X vezes
     */
    @Test
    void verifyTimesTest() {
        this.repository.findById(1l);
        this.repository.findById(1l);
        this.repository.findById(1l);
        verify(repository, times(3)).findById(1l);
    }

    /**
     * Verifica se o metodo sob teste
     * foi chamado ao menos 1 vez
     */
    @Test
    void verifyAtLeastOnceTest() {
        this.repository.findById(1l);
        this.repository.findById(1l);
        this.repository.findById(1l);
        verify(repository, atLeastOnce()).findById(1l);
    }

    /**
     * Verifica se o metodo sob teste
     * foi chamado ao menos X vezes
     */
    @Test
    void verifyAtLeastTest() {
        this.repository.findById(1l);
        this.repository.findById(1l);
        this.repository.findById(1l);
        verify(repository, atLeast(2)).findById(1l);
    }

    /**
     * Verifica se o metodo sob teste
     * foi chamado no máximo X vezes
     */
    @Test
    void verifyAtMostTest() {
        this.repository.findById(1l);
        this.repository.findById(1l);
        this.repository.findById(1l);
        verify(repository, atMost(4)).findById(1l);
    }

    /**
     * Verifica se o metodo sob teste
     * foi nunca foi chamado
     */
    @Test
    void verifyNeverTest() {
        this.repository.findById(1l);
        verify(repository, never()).findById(2l);
    }
}


