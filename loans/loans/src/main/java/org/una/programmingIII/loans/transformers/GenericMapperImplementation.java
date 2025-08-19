package org.una.programmingIII.loans.transformers;

import org.modelmapper.ModelMapper;
import java.util.List;

public class GenericMapperImplementation<E, D> implements GenericMapper<E, D> {
    private final ModelMapper mapper;
    private final Class<E> entityClass;
    private final Class<D> dtoClass;

    public GenericMapperImplementation(ModelMapper mapper, Class<E> entityClass, Class<D> dtoClass) {
        this.mapper = mapper; this.entityClass = entityClass; this.dtoClass = dtoClass;
    }

    @Override public D toDTO(E entity)   { return mapper.map(entity, dtoClass); }
    @Override public E toEntity(D dto)   { return mapper.map(dto, entityClass); }
    @Override public List<D> toDTOList(List<E> entities) { return entities.stream().map(this::toDTO).toList(); }
}
