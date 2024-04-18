package com.psi.project_psi.controller.freelance;

import com.psi.project_psi.errors.CustomResponseEntity;
import com.psi.project_psi.models.Certifications;
import com.psi.project_psi.service.CertificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
public class CertificationsController {
    @Autowired
    private CertificationService service;

    @PostMapping("/certification")
    public Certifications create(@RequestBody Certifications certifications){
        return service.create(certifications);
    }

    @GetMapping("/certification/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        Optional<Certifications> typeIndustry = service.getById(id);
        if (typeIndustry.isEmpty()) return CustomResponseEntity.fromKey("RESSOURCE_INTROUVABLE", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(typeIndustry, HttpStatus.OK);
    }

    @GetMapping("/certifications")
    public Iterable<Certifications> getAll(){
        return service.getAll();
    }

    @DeleteMapping("/certification/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        Optional<Certifications> deleteObject = service.getById(id);
        if (deleteObject.isPresent()) {
            service.delete(deleteObject.get());
            return CustomResponseEntity.fromKey("DELETE_SUCCESSFULLY", HttpStatus.OK);
        }else return CustomResponseEntity.fromKey("RESSOURCE_INTROUVABLE", HttpStatus.BAD_REQUEST);
    }
}
