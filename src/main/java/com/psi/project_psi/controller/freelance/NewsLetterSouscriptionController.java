package com.psi.project_psi.controller.freelance;

import com.psi.project_psi.models.NewsLetterSouscription;
import com.psi.project_psi.models.Ville;
import com.psi.project_psi.service.NewsLetterSouscriptionService;
import com.psi.project_psi.service.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class NewsLetterSouscriptionController {

    @Autowired
    private NewsLetterSouscriptionService souscriptionService;

    @PostMapping("/souscription")
    public NewsLetterSouscription create (@RequestBody NewsLetterSouscription souscription){

        return souscriptionService.create(souscription);
    }

    @GetMapping("/souscription/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        Optional<NewsLetterSouscription> souscription = souscriptionService.getById(id);
        if (!souscription.isPresent()) return new ResponseEntity<>("Cette souscription n'existe pas", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(souscription, HttpStatus.OK);
    }

    @GetMapping("/souscriptions")
    public Iterable<NewsLetterSouscription> getAll(){
        return souscriptionService.getAll();
    }


    @DeleteMapping("/souscription/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        souscriptionService.deleteById(id);
        return new ResponseEntity<>("Objet supprimé avec succès", HttpStatus.OK);
    }
}
