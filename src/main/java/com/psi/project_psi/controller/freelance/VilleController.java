package com.psi.project_psi.controller.freelance;

import com.psi.project_psi.errors.CustomResponseEntity;
import com.psi.project_psi.models.Ville;
import com.psi.project_psi.service.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
//@PreAuthorize("hasAnyAuthority('ADMIN','TEST')")
public class VilleController{

    @Autowired
    private VilleService villeService;

    @PostMapping("/ville")
    public Ville create (@RequestBody Ville ville){

        return villeService.create(ville);
    }

    @GetMapping("/ville/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        Optional<Ville> ville = villeService.getById(id);
        if (ville.isEmpty()) return CustomResponseEntity.fromKey("RESSOURCE_INTROUVABLE", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(ville, HttpStatus.OK);
    }

    @GetMapping("/villes")
    public Iterable<Ville> getAll(){
        return villeService.getAll();
    }

    @GetMapping("/ville/{idPays}")
    public List<Ville> getByPays(@PathVariable("idPays") Long idPays){
        return villeService.getByIdPays(idPays);
    }

    @DeleteMapping("/ville/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        Optional<Ville> deleteObject = villeService.getById(id);
        if (deleteObject.isPresent()) {
            villeService.delete(deleteObject.get());
            return CustomResponseEntity.fromKey("DELETE_SUCCESSFULLY", HttpStatus.OK);
        }else return new ResponseEntity<>("Not present", HttpStatus.BAD_REQUEST);
    }
}
