package com.psi.project_psi.controller;

import com.psi.project_psi.models.Enterprise;
import com.psi.project_psi.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class EnterpriseController {

    @Autowired
    private EnterpriseService enterpriseService;

    @PostMapping("/enterprise")
    public Enterprise create(@RequestBody Enterprise enterprise){
        return enterpriseService.create(enterprise);
    }

    @GetMapping("/enterprise/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id ){
        Optional<Enterprise> enterprise = enterpriseService.getById(id);
        if (!enterprise.isPresent()) return new ResponseEntity<>("Cette Entreprise n'existe pas", HttpStatus.OK);
        return new ResponseEntity<>(enterprise, HttpStatus.OK);
    }

    @GetMapping("/enterprises")
    public Iterable<Enterprise> getAll(){
        return enterpriseService.getAll();
    }

    @DeleteMapping("/enterprise/{id}")
    public void delete(@PathVariable("id") Long id){
        enterpriseService.deleteById(id);
    }
}
