package com.psi.project_psi.controller.freelance;

import com.psi.project_psi.errors.CustomResponseEntity;
import com.psi.project_psi.models.Domain;
import com.psi.project_psi.models.Module;
import com.psi.project_psi.service.DomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class DomainController {

    @Autowired
    DomainService domainService;

    @PostMapping("/domain")
    public Domain create(@RequestBody Domain domain){
        return domainService.create(domain);
    }

    @GetMapping("/domain/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        Optional<Domain> domain = domainService.getById(id);
        if (domain.isEmpty()) return CustomResponseEntity.fromKey("RESSOURCE_INTROUVABLE", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(domain, HttpStatus.OK);
    }

    @GetMapping("/domains")
    public Iterable<Domain> getAll(){
        return domainService.getAll();
    }

    @DeleteMapping("/domain/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        Optional<Domain> deleteObject = domainService.getById(id);
        if (deleteObject.isPresent()) {
            domainService.delete(deleteObject.get());
            return CustomResponseEntity.fromKey("DELETE_SUCCESSFULLY", HttpStatus.OK);
        }else return CustomResponseEntity.fromKey("RESSOURCE_INTROUVABLE", HttpStatus.BAD_REQUEST);
    }
}
