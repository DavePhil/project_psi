package com.psi.project_psi.service;

import com.psi.project_psi.models.BankAccount;
import com.psi.project_psi.repository.BankAccountRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class BankAccountService {
    @Autowired
    BankAccountRepository bankAccountRepository;

    public BankAccount create(BankAccount bankAccount){
        return bankAccountRepository.save(bankAccount);
    }

    public Iterable<BankAccount> getAll(){
        return bankAccountRepository.findAll();
    }

    public Optional<BankAccount> getById(Long id){
        return bankAccountRepository.findById(id);
    }

    public void delete (BankAccount deleteObject){
        deleteObject.setDelete(true);
        bankAccountRepository.save(deleteObject);
    }
}
