package com.psi.project_psi.repository;

import com.psi.project_psi.models.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByEmail(String email);
    Users findByEmailAndPassword(String email, String password);

}
