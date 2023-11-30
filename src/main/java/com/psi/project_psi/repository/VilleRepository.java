package com.psi.project_psi.repository;

import com.psi.project_psi.models.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VilleRepository extends JpaRepository<Ville, Long> {

    @Query("select v from Ville v inner join Pays p on p.id=v.pays.id where p.id=:idPays")
    List<Ville> findVilleByPays(Long idPays);

    Iterable<Ville> findAllByIsDeleteIsFalse();
}
