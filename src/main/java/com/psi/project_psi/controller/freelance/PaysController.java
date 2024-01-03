package com.psi.project_psi.controller.freelance;


import com.psi.project_psi.models.Pays;
import com.psi.project_psi.service.PaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class PaysController {

    @Autowired
    private PaysService paysService;

    @PostMapping("/pays")
    public Pays create(@RequestBody Pays pays){
        return paysService.create(pays);
    }

    @GetMapping("/pays/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        Optional<Pays> pays = paysService.getById(id);
        if (!pays.isPresent()) return new ResponseEntity<>("Ce Pays n'existe pas", HttpStatus.OK);
        return new ResponseEntity<>(pays, HttpStatus.OK);
    }

    @GetMapping("/pays")
    public Iterable<Pays> getAll(){
        return paysService.getAll();
    }

    @DeleteMapping("/pays/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        Optional<Pays> deleteObject = paysService.getById(id);
        if (deleteObject.isPresent()) {
            paysService.delete(deleteObject.get());
            return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
        }else return new ResponseEntity<>("Not present", HttpStatus.BAD_REQUEST);
    }

}
