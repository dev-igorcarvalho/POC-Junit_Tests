package br.com.igorcarvalho.tests.mockito;

import java.util.Map;

public class Service {

    public Service(Map<Long, String> repository) {
        this.repository = repository;
    }

    private final Map<Long, String> repository;


    public Map<Long, String> getRepository() {
        return this.repository;
    }
}
