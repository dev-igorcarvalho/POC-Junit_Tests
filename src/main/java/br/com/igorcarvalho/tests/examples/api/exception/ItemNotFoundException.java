package br.com.igorcarvalho.tests.examples.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by : 01001001 01000011 at 12/05/2021
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ItemNotFoundException extends Exception {
    public ItemNotFoundException(Long id) {
        super(String.format("Item with id %s is not found.", id));
    }

    public ItemNotFoundException() {
        super(String.format("No item was found."));
    }
}
