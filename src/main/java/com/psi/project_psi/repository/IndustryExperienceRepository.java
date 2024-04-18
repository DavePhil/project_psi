package com.psi.project_psi.repository;

import com.psi.project_psi.models.IndustryExperience;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndustryExperienceRepository extends JpaRepository<IndustryExperience, Long> {
    Iterable<IndustryExperience> findAllByIsDeleteIsFalse();
}
