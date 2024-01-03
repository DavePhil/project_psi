package com.psi.project_psi.controller.freelance;

import com.psi.project_psi.models.Ville;
import com.psi.project_psi.service.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
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
        if (!ville.isPresent()) return new ResponseEntity<>("Cette ville n'existe pas", HttpStatus.BAD_REQUEST);
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
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        villeService.deleteById(id);
        return new ResponseEntity<>("Objet supprimé avec succès", HttpStatus.OK);
    }
}
