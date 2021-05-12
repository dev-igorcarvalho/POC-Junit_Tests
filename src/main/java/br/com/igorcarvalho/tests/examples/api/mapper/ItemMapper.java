package br.com.igorcarvalho.tests.examples.api.mapper;

import br.com.igorcarvalho.tests.examples.api.dto.ItemDto;
import br.com.igorcarvalho.tests.examples.api.entity.ItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

/**
 * Created by : 01001001 01000011 at 12/05/2021
 */
@Mapper
@Component
public interface ItemMapper {
    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

    ItemEntity toModel(ItemDto dto);

    ItemDto toDTO(ItemEntity entity);
}
