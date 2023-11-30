package com.psi.project_psi.repository;

import com.psi.project_psi.models.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {

    @Query("select module from Module module where module.project.id=:idProject")
    List<Module> findByProject(@Param("idProject") Long idProject);

    Iterable<Module> findAllByIsDeleteIsFalse();
}
