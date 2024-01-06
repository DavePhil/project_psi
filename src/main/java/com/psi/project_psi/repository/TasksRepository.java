package com.psi.project_psi.repository;

import com.psi.project_psi.models.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TasksRepository extends JpaRepository<Tasks, Long> {

    @Query("select task from Tasks task where task.module.id=:idModule and task.isDelete=false ")
    List<Tasks> findTasksByModule(@Param("idModule") Long idModule);

    Iterable<Tasks> findAllByIsDeleteIsFalse();
}
