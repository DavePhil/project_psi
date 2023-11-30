package com.psi.project_psi.repository;

import com.psi.project_psi.models.Domain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomainRepository extends JpaRepository<Domain, Long> {
    Iterable<Domain> findAllByIsDeleteIsFalse();
}
