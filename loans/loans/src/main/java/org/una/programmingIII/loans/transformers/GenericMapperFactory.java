package org.una.programmingIII.loans.transformers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class GenericMapperFactory {
    private final ModelMapper modelMapper;

    public GenericMapperFactory(ModelMapper modelMapper) { // inyección por constructor
        this.modelMapper = modelMapper;
    }

    public <E, D> GenericMapper<E, D> createMapper(Class<E> entityClass, Class<D> dtoClass) {
        return new GenericMapperImplementation<>(modelMapper, entityClass, dtoClass);
    }
}
