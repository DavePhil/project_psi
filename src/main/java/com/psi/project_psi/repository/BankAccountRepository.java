package com.psi.project_psi.repository;

import com.psi.project_psi.models.BankAccount;
import com.psi.project_psi.models.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    Iterable<BankAccount> findAllByIsDeleteIsFalse();
}
