package com.psi.project_psi.controller.freelance;

import com.psi.project_psi.errors.CustomResponseEntity;
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
        if (typeOrganisation.isEmpty()) return CustomResponseEntity.fromKey("RESSOURCE_INTROUVABLE", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(typeOrganisation, HttpStatus.OK);
    }

    @GetMapping("/typesOrganisation")
    public Iterable<EnterpriseTypeOrganisation> getAll(){
        return typeOrganisationService.getAll();
    }

    @DeleteMapping("/typeOrganisation/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        Optional<EnterpriseTypeOrganisation> deleteObject = typeOrganisationService.getById(id);
        if (deleteObject.isPresent()) {
            typeOrganisationService.delete(deleteObject.get());
            return CustomResponseEntity.fromKey("DELETE_SUCCESSFULLY", HttpStatus.OK);
        }else return CustomResponseEntity.fromKey("RESSOURCE_INTROUVABLE", HttpStatus.BAD_REQUEST);
    }
}
