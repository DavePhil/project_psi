package com.psi.project_psi.repository.ForAdmin;

import com.psi.project_psi.models.ForAdmin.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvenementRepository extends JpaRepository<Evenement, Long> {
    Iterable<Evenement> findAllByIsDeleteIsFalse();
}
