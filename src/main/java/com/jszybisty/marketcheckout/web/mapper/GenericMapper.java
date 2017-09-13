package com.jszybisty.marketcheckout.web.mapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jakub on 30.08.2017.
 */
public interface GenericMapper<E, D> {

    E mapToEntity(D dto);

    D mapToDto(E entity);

    default List<E> mapToEntityList(List<D> dtos) {
        return dtos.stream().map(this::mapToEntity).collect(Collectors.toList());
    }

    default List<D> mapToDtoList(List<E> entities) {
        return entities.stream().map(this::mapToDto).collect(Collectors.toList());
    }
}
