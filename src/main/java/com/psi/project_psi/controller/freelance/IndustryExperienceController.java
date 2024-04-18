package com.psi.project_psi.controller.freelance;

import com.psi.project_psi.errors.CustomResponseEntity;
import com.psi.project_psi.models.IndustryExperience;
import com.psi.project_psi.service.IndustryExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class IndustryExperienceController {
    @Autowired
    private IndustryExperienceService service;

    @PostMapping("/industryExperience")
    public IndustryExperience create(@RequestBody IndustryExperience industryExperience){
        return service.create(industryExperience);
    }

    @GetMapping("/industryExperience/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        Optional<IndustryExperience> typeIndustry = service.getById(id);
        if (typeIndustry.isEmpty()) return CustomResponseEntity.fromKey("RESSOURCE_INTROUVABLE", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(typeIndustry, HttpStatus.OK);
    }

    @GetMapping("/industryExperiences")
    public Iterable<IndustryExperience> getAll(){
        return service.getAll();
    }

    @DeleteMapping("/industryExperience/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        Optional<IndustryExperience> deleteObject = service.getById(id);
        if (deleteObject.isPresent()) {
            service.delete(deleteObject.get());
            return CustomResponseEntity.fromKey("DELETE_SUCCESSFULLY", HttpStatus.OK);
        }else return CustomResponseEntity.fromKey("RESSOURCE_INTROUVABLE", HttpStatus.BAD_REQUEST);
    }
}
