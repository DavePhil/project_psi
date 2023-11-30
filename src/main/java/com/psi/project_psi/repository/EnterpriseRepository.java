package com.psi.project_psi.repository;

import com.psi.project_psi.models.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {

    @Query("select enterprise from Enterprise enterprise where enterprise.users.id=:idUser")
    List<Enterprise> getEnterpriseByUsers(@Param("idUser") Long idUser);
    Iterable<Enterprise> findAllByIsDeleteIsFalse();
}
