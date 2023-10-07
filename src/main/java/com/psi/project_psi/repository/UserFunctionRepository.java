package com.psi.project_psi.repository;
import com.psi.project_psi.models.UserFunction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFunctionRepository extends JpaRepository<UserFunction, Long> {
}
