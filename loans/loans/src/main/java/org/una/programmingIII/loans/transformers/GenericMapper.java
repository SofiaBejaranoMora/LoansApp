package org.una.programmingIII.loans.transformers;

public interface GenericMapper<E, D> {
   D convertToDTO(E entity);
   E convertToEntity(D dto);
}
