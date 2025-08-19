package org.una.programmingIII.loans.transformers;

import java.util.List;

public interface GenericMapper<E, D> {
    // Nombres base
    D toDTO(E entity);
    E toEntity(D dto);
    List<D> toDTOList(List<E> entities);

    // === Alias que suelen llamar los services ===
    default D convertToDTO(E entity)            { return toDTO(entity); }
    default E convertToEntity(D dto)            { return toEntity(dto); }
    default List<D> convertToDTOList(List<E> l) { return toDTOList(l); }
}
