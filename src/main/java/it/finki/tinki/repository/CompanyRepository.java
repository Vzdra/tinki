package it.finki.tinki.repository;

import it.finki.tinki.model.Users.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findByEmailAndPassword(String email, String password);
    Optional<Company> findByEmail(String email);
    Optional<Company> findByIdAndEmail(Long id, String email);
}
