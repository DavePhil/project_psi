package com.psi.project_psi.repository;

import com.psi.project_psi.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    @Query("select profile from Profile profile where profile.users.id =:idUser")
    List<Profile> findProfileByUsers(@Param("idUser") Long idUser);
}
