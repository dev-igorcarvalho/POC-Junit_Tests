package br.com.igorcarvalho.tests.mockito;

public class FakeEntity {
    private Long id;
    private String nome;

    public FakeEntity(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public FakeEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
