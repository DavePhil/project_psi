package com.psi.project_psi.controller.freelance;

import com.psi.project_psi.errors.CustomResponseEntity;
import com.psi.project_psi.models.BankAccount;

import com.psi.project_psi.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @PostMapping("/bankaccount")
    public BankAccount create(@RequestBody BankAccount bankAccount){
        return bankAccountService.create(bankAccount);
    }

    @GetMapping("/bankaccount/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        Optional<BankAccount> bankAccount = bankAccountService.getById(id);
        if (bankAccount.isEmpty()) return CustomResponseEntity.fromKey("RESSOURCE_INTROUVABLE", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(bankAccount, HttpStatus.OK);
    }

    @GetMapping("/bankaccounts")
    public Iterable<BankAccount> getAll(){
        return bankAccountService.getAll();
    }

    @DeleteMapping("/bankaccount/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        Optional<BankAccount> deleteObject = bankAccountService.getById(id);
        if (deleteObject.isPresent()) {
            bankAccountService.delete(deleteObject.get());
            return CustomResponseEntity.fromKey("DELETE_SUCCESSFULLY", HttpStatus.OK);
        }else return CustomResponseEntity.fromKey("RESSOURCE_INTROUVABLE", HttpStatus.BAD_REQUEST);
    }

}
