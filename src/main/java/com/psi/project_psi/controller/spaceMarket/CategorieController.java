package com.psi.project_psi.controller.spaceMarket;

import com.psi.project_psi.errors.CustomResponseEntity;
import com.psi.project_psi.models.Categorie;
import com.psi.project_psi.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CategorieController {
    @Autowired
    private CategorieService categorieService;

    @PostMapping("/categorie")
    public Categorie create(@RequestBody Categorie categorie){
        return categorieService.create(categorie);
    }

    @GetMapping("/categorie/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        Optional<Categorie> categorie = categorieService.getById(id);
        if (categorie.isEmpty()) return CustomResponseEntity.fromKey("RESSOURCE_INTROUVABLE", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(categorie, HttpStatus.OK);
    }

    @GetMapping("/categories")
    public Iterable<Categorie> getAll(){
        return categorieService.getAll();
    }

    @DeleteMapping("/categorie/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        Optional<Categorie> deleteObject = categorieService.getById(id);
        if (deleteObject.isPresent()) {
            categorieService.delete(deleteObject.get());
            return CustomResponseEntity.fromKey("DELETE_SUCCESSFULLY", HttpStatus.OK);
        }else return CustomResponseEntity.fromKey("RESSOURCE_INTROUVABLE", HttpStatus.BAD_REQUEST);
    }
}
