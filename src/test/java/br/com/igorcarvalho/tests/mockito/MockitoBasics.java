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
@DisplayName("Mockito tests")
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

    @Captor
    ArgumentCaptor<FakeEntity> entityCaptor;


    @BeforeEach
    void init() {
        /**
         * So pode ser colocada uma configuração
         * dessas por classe nos metodos before
         * e nao pode ser repetido nenhuma outra
         * configuração dentro do corpo dos metodos
         * de teste.
         * Por esa razão a configuração abaixo esta comentada.
         * é necessario descomenta-la para usar no metodo
         * findByIdTest2()
         * */
//        when(this.repository.findById(2L)).thenReturn(new FakeEntity(2L, "mokito test"));
    }

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
     * Apenas uma configuração deve ser usada no beforeEach
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


