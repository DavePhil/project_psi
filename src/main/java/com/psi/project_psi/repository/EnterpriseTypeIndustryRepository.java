package com.psi.project_psi.repository;


import com.psi.project_psi.models.EnterpriseTypeIndustry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnterpriseTypeIndustryRepository extends JpaRepository<EnterpriseTypeIndustry, Long> {
    Iterable<EnterpriseTypeIndustry> findAllByIsDeleteIsFalse();
}
