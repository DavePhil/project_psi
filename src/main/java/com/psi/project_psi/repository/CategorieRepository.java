package com.psi.project_psi.repository;

import com.psi.project_psi.models.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie,Long> {
    Iterable<Categorie> findAllByIsDeleteIsFalse();
}
