package com.psi.project_psi.repository;

import com.psi.project_psi.models.Competences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetencesRepository extends JpaRepository<Competences, Long> {
}
