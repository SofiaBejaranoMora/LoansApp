package org.una.programmingIII.loans.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.una.programmingIII.loans.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
   Role findByName(String name);
}
