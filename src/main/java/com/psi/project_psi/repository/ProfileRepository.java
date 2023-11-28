package com.psi.project_psi.repository;

import com.psi.project_psi.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    @Query("select profile from Profile profile where profile.users.id =:idUser")
    Optional<Profile> findProfileByUsers(@Param("idUser") Long idUser);
    @Query("select profile from Profile profile inner join Domain domain on domain.id=profile.domain.id")
    List<Profile> findProfileByDomain(@Param("idDomain") Long idDomain);
}
