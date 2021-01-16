package br.com.igorcarvalho.tests.mockito;

public class FakeRepository {

    public FakeEntity findById(Long id){
        return new FakeEntity();
    }

    public FakeEntity save(FakeEntity entity) {
        return new FakeEntity();
    }

    public FakeEntity update(FakeEntity entity) {
        return new FakeEntity();
    }

}
