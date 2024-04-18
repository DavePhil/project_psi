package com.psi.project_psi.repository.ForAdmin;


import com.psi.project_psi.models.ForAdmin.Tendances;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TendancesRepository extends JpaRepository<Tendances, Long> {
    Iterable<Tendances> findAllByIsDeleteIsFalse();
}
