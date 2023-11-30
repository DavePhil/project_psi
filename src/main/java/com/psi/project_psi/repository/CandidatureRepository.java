package com.psi.project_psi.repository;

import com.psi.project_psi.models.Candidature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatureRepository extends JpaRepository<Candidature, Long> {

    Iterable<Candidature> findAllByIsDeleteIsFalse();
}
