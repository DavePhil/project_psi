package com.psi.project_psi.controller.spaceMarket;

import com.psi.project_psi.models.Article;
import com.psi.project_psi.models.Categorie;
import com.psi.project_psi.models.Commandes;
import com.psi.project_psi.models.State;
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

    @GetMapping("/commandes")
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
    @PutMapping("/commande/valide/{id}")
    public ResponseEntity<?> valide(@PathVariable("id") Long id){
        Optional<Commandes> article = commandeService.getById(id);
        if (article.isPresent()) return new ResponseEntity<>("Cette commande n'existe pas", HttpStatus.BAD_REQUEST);
        commandeService.modifyState(State.Valide,id);
        return new ResponseEntity<>("commande validée", HttpStatus.OK);
    }
    @PutMapping("/commande/rejette/{id}")
    public ResponseEntity<?> rejette(@PathVariable("id") Long id){
        Optional<Commandes> article = commandeService.getById(id);
        if (article.isPresent()) return new ResponseEntity<>("Cette commande n'existe pas", HttpStatus.BAD_REQUEST);
        commandeService.modifyState(State.Valide,id);
        return new ResponseEntity<>("commande rejetée", HttpStatus.OK);
    }
    @PutMapping("/commande/annule/{id}")
    public ResponseEntity<?> annule(@PathVariable("id") Long id){
        Optional<Commandes> article = commandeService.getById(id);
        if (article.get().getState() == State.Valide) return new ResponseEntity<>("Cette commande a déjà été validée", HttpStatus.OK);
        if (article.isPresent()) return new ResponseEntity<>("Cet commande n'existe pas", HttpStatus.BAD_REQUEST);
        commandeService.modifyState(State.Valide,id);
        return new ResponseEntity<>("commande annulée", HttpStatus.OK);
    }

}
