package com.psi.project_psi.repository;

import com.psi.project_psi.models.Competences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompetencesRepository extends JpaRepository<Competences, Long> {
    @Query("select competences from Competences competences where competences.domain.id=:idDomain")
    List<Competences> findCompetencesByDomain(@Param("idDomain") Long idDomain);

    Iterable<Competences> findAllByIsDeleteIsFalse();
}
