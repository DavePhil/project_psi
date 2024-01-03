package com.psi.project_psi.repository;

import com.psi.project_psi.models.Commandes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandeRepository extends JpaRepository<Commandes, Long> {
    Iterable<Commandes> findAllByIsDeleteIsFalse();
}
