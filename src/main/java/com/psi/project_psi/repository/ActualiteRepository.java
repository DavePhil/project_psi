package com.psi.project_psi.repository;
import com.psi.project_psi.models.Actualite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActualiteRepository extends JpaRepository<Actualite, Long> {

    Iterable<Actualite> findAllByIsDeleteIsFalse();
}
