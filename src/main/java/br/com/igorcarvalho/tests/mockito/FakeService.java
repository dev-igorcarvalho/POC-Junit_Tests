package br.com.igorcarvalho.tests.mockito;

public class FakeService {

    public FakeService(FakeRepository repository, FakeEntityFactory factory) {
        this.repository = repository;
        this.factory = factory;
    }

    public FakeService(FakeRepository repository) {
        this.repository = repository;
    }

    private final FakeRepository repository;
    private FakeEntityFactory factory;


    public FakeRepository getRepository() {
        return this.repository;
    }

    public void update(FakeEntity entity) {
        if (entity.getId() != null) {
            getRepository().update(entity);
        }
    }

    public void saveRandom() {
        final FakeEntity instance = factory.getInstance();
        getRepository().save(instance);
    }


}
