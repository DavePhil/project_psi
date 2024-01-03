package com.psi.project_psi.controller.freelance;

import com.psi.project_psi.models.Speciality;
import com.psi.project_psi.service.SpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class SpecialityController {

    @Autowired
    private SpecialityService specialityService;

    @PostMapping("/speciality")
    public Speciality create(@RequestBody Speciality speciality){
        return specialityService.create(speciality);
    }

    @GetMapping("/speciality/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        Optional<Speciality> speciality = specialityService.getById(id);
        if (!speciality.isPresent()) return new ResponseEntity<>("Cette spécialité est manquante", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(speciality, HttpStatus.OK);
    }

    @GetMapping("/specialities")
    public Iterable<Speciality> getAll(@PathVariable("id")Long id){
        return specialityService.getAll();
    }

    @DeleteMapping("/speciality/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        Optional<Speciality> deleteObject = specialityService.getById(id);
        if (deleteObject.isPresent()) {
            specialityService.delete(deleteObject.get());
            return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
        }else return new ResponseEntity<>("Not present", HttpStatus.BAD_REQUEST);
    }
}
