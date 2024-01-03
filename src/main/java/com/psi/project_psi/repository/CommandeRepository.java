package com.psi.project_psi.repository;

import com.psi.project_psi.models.Commandes;
import com.psi.project_psi.models.State;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandeRepository extends JpaRepository<Commandes, Long> {
    Iterable<Commandes> findAllByIsDeleteIsFalse();
    @Modifying
    @Transactional
    @Query("update Commandes commande set commande.state = ?1 where commande.id = ?2")
    int modifyState(State articleState, Long id);
}
