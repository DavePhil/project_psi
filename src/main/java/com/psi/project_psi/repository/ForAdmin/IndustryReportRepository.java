package com.psi.project_psi.repository.ForAdmin;

import com.psi.project_psi.models.ForAdmin.IndustryReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndustryReportRepository extends JpaRepository<IndustryReport, Long> {
    Iterable<IndustryReport> findAllByIsDeleteIsFalse();
}
