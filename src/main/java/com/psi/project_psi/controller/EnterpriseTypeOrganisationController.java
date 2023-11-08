package com.psi.project_psi.controller;

import com.psi.project_psi.models.EnterpriseTypeOrganisation;
import com.psi.project_psi.service.EnterpriseTypeOrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class EnterpriseTypeOrganisationController {

    @Autowired
    private EnterpriseTypeOrganisationService typeOrganisationService;

    @PostMapping("/typeOrganisation")
    public EnterpriseTypeOrganisation create(@RequestBody EnterpriseTypeOrganisation typeOrganisation){
        return typeOrganisationService.create(typeOrganisation);
    }

    @GetMapping("/typeOrganisation/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        Optional<EnterpriseTypeOrganisation> typeOrganisation = typeOrganisationService.getById(id);
        if (!typeOrganisation.isPresent()) return new ResponseEntity<>("Ce type d'organisation n'est pas présent", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(typeOrganisation, HttpStatus.OK);
    }

    @GetMapping("/typesOrganisation")
    public Iterable<EnterpriseTypeOrganisation> getAll(){
        return typeOrganisationService.getAll();
    }

    @DeleteMapping("/typeOrganisation/{id}")
    public void delete(@PathVariable("id") Long id){
        typeOrganisationService.deleteById(id);
    }
}
