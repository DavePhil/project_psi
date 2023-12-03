package com.psi.project_psi.repository;

import com.psi.project_psi.models.Candidature;
import com.psi.project_psi.models.State;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatureRepository extends JpaRepository<Candidature, Long> {

    Iterable<Candidature> findAllByIsDeleteIsFalse();
    @Modifying
    @Transactional
    @Query("update Candidature candidature set candidature.state = ?1 where candidature.id = ?2")
    int modifyState(State state, Long idCandidature);
}
