package com.psi.project_psi.controller.freelance;

import com.psi.project_psi.errors.CustomResponseEntity;
import com.psi.project_psi.models.Competences;
import com.psi.project_psi.models.Module;
import com.psi.project_psi.service.CompetencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CompetencesController {

    @Autowired
    private CompetencesService competencesService;

    @PostMapping("/competence")
    public Competences create(@RequestBody Competences competence){
        return competencesService.create(competence);
    }

    @GetMapping("/competence/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        Optional<Competences> competences = competencesService.getById(id);
        if (competences.isEmpty()) return CustomResponseEntity.fromKey("RESSOURCE_INTROUVABLE", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(competences, HttpStatus.OK);
    }

    @GetMapping("/competences")
    public Iterable<Competences> getAll(){
        return competencesService.getAll();
    }

    @GetMapping("/competencesbydomain/{idDomain}")
    public List<Competences> getByDomain(@PathVariable("idDomain") Long idDomain){
        return competencesService.findByDomain(idDomain);
    }

    @DeleteMapping("/competence/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        Optional<Competences> deleteObject = competencesService.getById(id);
        if (deleteObject.isPresent()) {
            competencesService.delete(deleteObject.get());
            return CustomResponseEntity.fromKey("DELETE_SUCCESSFULLY", HttpStatus.OK);
        }else return CustomResponseEntity.fromKey("RESSOURCE_INTROUVABLE", HttpStatus.BAD_REQUEST);
    }
}
