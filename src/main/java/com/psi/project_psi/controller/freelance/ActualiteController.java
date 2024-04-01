package com.psi.project_psi.controller.freelance;

import com.psi.project_psi.errors.CustomResponseEntity;
import com.psi.project_psi.models.Actualite;
import com.psi.project_psi.models.Admin;
import com.psi.project_psi.service.ActualiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
public class ActualiteController {

    @Autowired
    private ActualiteService actualiteService;

    @PostMapping("/actualite")
    public Actualite create(@RequestParam("title") String title, @RequestParam(value = "description", required = false) String description,
                            @RequestParam(value = "image", required = false)MultipartFile image , @RequestParam(value = "admin", required = false) Admin admin) throws IOException {
        return actualiteService.create(title,description,image,admin);
    }

    @GetMapping("/actualite/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        Optional<Actualite> newsLetters = actualiteService.getById(id);
        if (newsLetters.isEmpty()) return CustomResponseEntity.fromKey("RESSOURCE_INTROUVABLE", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(newsLetters, HttpStatus.OK);
    }

    @GetMapping("/actualites")
    public Iterable<Actualite> getAll(){
        return actualiteService.getAll();
    }

    @DeleteMapping("/actualite/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        Optional<Actualite> deleteObject = actualiteService.getById(id);
        if (deleteObject.isPresent()) {
            actualiteService.delete(deleteObject.get());
            return CustomResponseEntity.fromKey("DELETE_SUCCESSFULLY", HttpStatus.OK);
        }else return CustomResponseEntity.fromKey("RESSOURCE_INTROUVABLE", HttpStatus.BAD_REQUEST);
    }
}
