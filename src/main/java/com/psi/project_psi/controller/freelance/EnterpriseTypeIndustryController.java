package com.psi.project_psi.controller.freelance;

import com.psi.project_psi.models.EnterpriseTypeIndustry;
import com.psi.project_psi.service.EnterpriseTypeIndustryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class EnterpriseTypeIndustryController {
    @Autowired
    private EnterpriseTypeIndustryService typeIndustryService;

    @PostMapping("/typeIndustry")
    public EnterpriseTypeIndustry create(@RequestBody EnterpriseTypeIndustry typeIndustry){
        return typeIndustryService.create(typeIndustry);
    }

    @GetMapping("/typeIndustry/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        Optional<EnterpriseTypeIndustry> typeIndustry = typeIndustryService.getById(id);
        if (!typeIndustry.isPresent()) return new ResponseEntity<>("Ce type d'industrie n'est pas présent", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(typeIndustry, HttpStatus.OK);
    }

    @GetMapping("/typesIndustry")
    public Iterable<EnterpriseTypeIndustry> getAll(){
        return typeIndustryService.getAll();
    }

    @DeleteMapping("/typeIndustry/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        Optional<EnterpriseTypeIndustry> deleteObject = typeIndustryService.getById(id);
        if (deleteObject.isPresent()) {
            typeIndustryService.delete(deleteObject.get());
            return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
        }else return new ResponseEntity<>("Not present", HttpStatus.BAD_REQUEST);
    }
}
