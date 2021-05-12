package br.com.igorcarvalho.tests.examples.api.service;

import br.com.igorcarvalho.tests.examples.api.dto.ItemDto;
import br.com.igorcarvalho.tests.examples.api.entity.ItemEntity;
import br.com.igorcarvalho.tests.examples.api.exception.ItemNotFoundException;
import br.com.igorcarvalho.tests.examples.api.mapper.ItemMapper;
import br.com.igorcarvalho.tests.examples.api.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by : 01001001 01000011 at 12/05/2021
 */
@Service
public class ItemService {

    private final ItemRepository repository;
    private final ItemMapper mapper;

    public ItemService(ItemRepository repository) {
        this.repository = repository;
        mapper = ItemMapper.INSTANCE;
    }

    public ItemDto save(ItemDto dto) {
        final ItemEntity entity =
                repository.save(mapper.toModel(dto));
        return mapper.toDTO(entity);
    }

    public ItemDto findById(Long id) throws ItemNotFoundException {
        final ItemEntity entity =
                repository.findById(id)
                        .orElseThrow(() -> new ItemNotFoundException(id));
        return mapper.toDTO(entity);
    }

    public List<ItemDto> listAll() throws ItemNotFoundException {
        List<ItemEntity> result = repository.findAll();
        if (result == null && result.isEmpty()) {
            throw new ItemNotFoundException();
        }
        return result.stream().map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) throws ItemNotFoundException {
        verifyIfExists(id);
        repository.deleteById(id);
    }

    public ItemDto update(Long id, ItemDto dto) throws ItemNotFoundException {
        verifyIfExists(id);
        final ItemEntity entity = mapper.toModel(dto);
        entity.setId(id);
        return mapper.toDTO(repository.save(entity));

    }

    private ItemEntity verifyIfExists(Long id) throws ItemNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));
    }
}
