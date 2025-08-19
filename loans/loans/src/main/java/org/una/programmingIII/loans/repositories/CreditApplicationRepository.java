package org.una.programmingIII.loans.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.una.programmingIII.loans.models.CreditApplication;

import java.util.List;

@Repository
public interface CreditApplicationRepository extends JpaRepository<CreditApplication, Long> {
   List<CreditApplication> findByApplicantId(Long applicantId);
}
