package com.psi.project_psi.repository;

import com.psi.project_psi.models.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialityRepository extends JpaRepository<Speciality, Long> {
    Iterable<Speciality> findAllByIsDeleteIsFalse();
}
