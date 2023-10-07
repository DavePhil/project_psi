package com.psi.project_psi.repository;

import com.psi.project_psi.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findAdminByEmail(String email);

    Optional<Admin> findAdminByEmailAndPassword(String email, String password);

}
