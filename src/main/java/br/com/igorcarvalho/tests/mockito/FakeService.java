package br.com.igorcarvalho.tests.mockito;

public class FakeService {

    public FakeService(FakeRepository repository) {
        this.repository = repository;
    }

    private final FakeRepository repository;


    public FakeRepository getRepository() {
        return this.repository;
    }

    public void update(FakeEntity entity) {
        if (entity.getId() != null) {
            getRepository().update(entity);
        }
    }


}
