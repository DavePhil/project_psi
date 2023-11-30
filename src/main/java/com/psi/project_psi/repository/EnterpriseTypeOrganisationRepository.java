package com.psi.project_psi.repository;

import com.psi.project_psi.models.EnterpriseTypeOrganisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnterpriseTypeOrganisationRepository extends JpaRepository<EnterpriseTypeOrganisation, Long> {
    Iterable<EnterpriseTypeOrganisation> findAllByIsDeleteIsFalse();
}
