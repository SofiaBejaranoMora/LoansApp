package org.una.programmingIII.loans.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.una.programmingIII.loans.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   User findByEmail(String email);
}
