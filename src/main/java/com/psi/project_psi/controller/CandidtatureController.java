package com.psi.project_psi.controller;


import com.psi.project_psi.models.BankAccount;
import com.psi.project_psi.models.Candidature;

import com.psi.project_psi.service.CandidatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CandidtatureController {

    @Autowired
    private CandidatureService candidatureService;

    @PostMapping("/candidature")
    public Candidature create(@RequestBody Candidature candidature){
        return candidatureService.create(candidature);
    }

    @GetMapping("/candidature/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        Optional<Candidature> candidature = candidatureService.getById(id);
        if (!candidature.isPresent()) return new ResponseEntity<>("Cette candidature n'est pas présente", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(candidature, HttpStatus.OK);
    }

    @GetMapping("/candidatures")
    public Iterable<Candidature> getAll(){
        return candidatureService.getAll();
    }

    @DeleteMapping("/candidature/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        Optional<Candidature> deleteObject = candidatureService.getById(id);
        if (deleteObject.isPresent()) {
            candidatureService.delete(deleteObject.get());
            return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
        }else return new ResponseEntity<>("Not present", HttpStatus.BAD_REQUEST);
    }


}
