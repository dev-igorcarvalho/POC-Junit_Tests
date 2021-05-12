package br.com.igorcarvalho.tests.examples.api.controller;

import br.com.igorcarvalho.tests.examples.api.dto.ItemDto;
import br.com.igorcarvalho.tests.examples.api.exception.ItemNotFoundException;
import br.com.igorcarvalho.tests.examples.api.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(ItemController.BASE_URL)
public final class ItemController {
    public static final String BASE_URL = "/api/v1/item";

    private final ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDto createBeer(@RequestBody ItemDto request)  {
        return service.save(request);
    }

    @GetMapping("/{id}")
    public ItemDto findByName(@PathVariable Long id) throws ItemNotFoundException {
        return service.findById(id);
    }

    @GetMapping
    public List<ItemDto> listItems() throws ItemNotFoundException {
        return service.listAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws ItemNotFoundException {
        service.deleteById(id);
    }

    @PutMapping("/{id}/increment")
    public ItemDto increment(@PathVariable Long id, @RequestBody ItemDto request) throws ItemNotFoundException {
        return service.update(id, request);
    }
}
