package com.psi.project_psi.controller.spaceMarket;

import com.psi.project_psi.models.Categorie;
import com.psi.project_psi.models.Commandes;
import com.psi.project_psi.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CommandeController {
    @Autowired
    private CommandeService commandeService;

    @PostMapping("/commande")
    public Commandes create(@RequestBody Commandes commandes){
        return commandeService.create(commandes);
    }

    @GetMapping("/commande/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        Optional<Commandes> commande = commandeService.getById(id);
        if (!commande.isPresent()) return new ResponseEntity<>("Cette commande", HttpStatus.OK);
        return new ResponseEntity<>(commande, HttpStatus.OK);
    }

    @GetMapping("/comandes")
    public Iterable<Commandes> getAll(){
        return commandeService.getAll();
    }

    @DeleteMapping("/commande/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        Optional<Commandes> deleteObject = commandeService.getById(id);
        if (deleteObject.isPresent()) {
            commandeService.delete(deleteObject.get());
            return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
        }else return new ResponseEntity<>("Not present", HttpStatus.BAD_REQUEST);
    }
}
