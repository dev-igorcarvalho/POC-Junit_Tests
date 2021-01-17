package br.com.igorcarvalho.tests.mockito;

public class FakeEntityFactory {

    public FakeEntityFactory() {
    }

    public FakeEntity getInstance() {
        return new FakeEntity(System.currentTimeMillis(), String.valueOf(System.currentTimeMillis()));
    }
}
