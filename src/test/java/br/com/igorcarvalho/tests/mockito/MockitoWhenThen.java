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
@DisplayName("Mockito when then")
public class MockitoWhenThen {

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

    @Captor
    ArgumentCaptor<FakeEntity> entityCaptor;


    @BeforeEach
    void init() {
        /**
         * Toda configuração usada nos metodos before
         * tem que ser usada em algum teste, senão o
         * mockito joga uma exception reclamando de
         * stub nao utilizada que esta sujando a classe
         * com codigo morto
         * */
        when(this.repository.findById(2L)).thenReturn(new FakeEntity(2L, "mokito test"));
    }

    /**
     * ao configurar um when.then o mockito
     * garante q toda vez q o metodo for chamado
     * ele vai retornar um dublê do valor desejado
     * simulando um retorno de alguma dependencia, banco,
     * webservice, etc
     * Para tornar o codigo mais elegante esse comportamento pode
     * ser configurado em metodos before do Junit
     */
    @Test
    void findByIdTest() {
        when(this.repository.findById(1L)).thenReturn(new FakeEntity(1L, "mokito test"));
        final FakeEntity entity = this.repository.findById(1L);
        assertAll("falhou em buscar a entidade correta",
                () -> verify(repository).findById(1l),
                () -> assertNotNull(entity.getId()));
    }

    /**
     * Faz exatamente o q o metodo anterior faz
     * porem a configuração do comportamento do mock
     * esta no @BeforeEach para garantir que todos os
     * metodos que usam esse mock vao se comportar igual
     * e garantir a qualidade semantica no corpo do metodo
     * contendo apenas codigo referente ao teste
     */
    @Test
    void findByIdTest2() {
        final FakeEntity entity = this.repository.findById(2L);
        assertAll("falhou em buscar a entidade correta",
                () -> verify(repository).findById(2l),
                () -> assertNotNull(entity.getId()));
    }

    /**
     * Neste exemplo ao configurar o comportamento
     * fizemos uso do metodo any() que  recebe como
     * parametro o tipo da classe q é solicitada na
     * assinadtura do metodo sob teste
     * Também existe a possibilidade de usar varios outros
     * metodos any() pré configurados  como anyInt(),
     * anyBoolean(), anyList(), anyMap(), anySet(), etc
     */
    @Test
    void saveTest() {
        //arrange
        when(this.repository.save(any(FakeEntity.class)))
                .thenReturn(new FakeEntity(2L, "mokito test"));
        final FakeEntity unsaved = new FakeEntity();
        //act
        final FakeEntity saved = repository.save(unsaved);
        //assert
        assertAll("falhou em salvar a entidade",
                () -> verify(repository).save(unsaved),//poderia usa o any() tb
                () -> assertNotNull(saved.getId()));
    }

    /**
     * Faz o mesmo que o teste anterior
     * porem escrito de forma diferente
     * usando o estilo de BDD do mockito
     */
    @Test
    void saveBddTest() {
        //given
        final FakeEntity unsaved = new FakeEntity();
        given(this.repository.save(any(FakeEntity.class)))
                .willReturn(new FakeEntity(2L, "mokito test"));
        //when
        final FakeEntity saved = repository.save(unsaved);
        //then
        assertAll("falhou em salvar a entidade",
                () -> then(repository).should().save(unsaved),//poderia usa o any() tb
                () -> assertNotNull(saved.getId()));
    }


    /**
     * ArgumentCaptor serve para capturar
     * os argumentos usados em um metodo que
     * nao se tem acesso direto pelo Sut
     * Pode ser criado inline dentro do proprio teste
     * ou criado de forma global via annotation
     *
     * @Captor ArgumentCaptor<T> captor;
     */
    @Test
    public void inlineArgumentCaptorTest() {
        //given
        final FakeEntity entity = new FakeEntity(1l, "update");
        final ArgumentCaptor<FakeEntity> captor =
                ArgumentCaptor.forClass(FakeEntity.class);
        //when
        service.update(entity);
        //then
        assertAll(
                "Falhou em atualizar a entidade",
                () -> verify(repository).update(captor.capture()),
                () -> assertNotNull(captor.getValue().getId()),
                () -> assertEquals("update", captor.getValue().getNome()),
                () -> assertEquals(Long.valueOf(1), captor.getValue().getId())
        );
    }

    @Test
    public void annotationArgumentCaptorTest() {
        //given
        final FakeEntity entity = new FakeEntity(1l, "update");
        //when
        service.update(entity);
        //then
        assertAll(
                "Falhou em atualizar a entidade",
                () -> verify(repository).update(entityCaptor.capture()),
                () -> assertNotNull(entityCaptor.getValue().getId()),
                () -> assertEquals("update", entityCaptor.getValue().getNome()),
                () -> assertEquals(Long.valueOf(1), entityCaptor.getValue().getId())
        );
    }
}


