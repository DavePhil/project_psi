package com.psi.project_psi.repository;

import com.psi.project_psi.models.Profile;
import com.psi.project_psi.models.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByEmail(String email);
    List<Users> findAllByIsDeleteIsFalse();
}
