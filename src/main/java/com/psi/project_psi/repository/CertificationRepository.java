package com.psi.project_psi.repository;

import com.psi.project_psi.models.Certifications;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificationRepository extends JpaRepository<Certifications, Long> {
    Iterable<Certifications> findAllByIsDeleteIsFalse();
}
