package com.psi.project_psi.repository;

import com.psi.project_psi.models.Project;
import com.psi.project_psi.models.State;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("select project from Project project where project.users.id =:idUser")
    List<Project> findProjectByUsers(@Param("idUser") Long idUser);
    Iterable<Project> findAllByIsDeleteIsFalse();

//    @Modifying
//    @Transactional
//    @Query("update Project project set project.state = ?1 where project.id = ?2")
//    int modifyState(State state, Long id);
}
