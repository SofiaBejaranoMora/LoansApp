package org.una.programmingIII.loans.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.una.programmingIII.loans.models.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
}
