package com.psi.project_psi.repository;

import com.psi.project_psi.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("select project from Project project where project.users.id =:idUser")
    List<Project> findProjectByUsers(@Param("idUser") Long idUser);

    Iterable<Project> findAllByIsDeleteIsFalse();
}
