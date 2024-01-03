package com.psi.project_psi.service;

import com.psi.project_psi.models.Article;
import com.psi.project_psi.models.Commandes;
import com.psi.project_psi.models.State;
import com.psi.project_psi.repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommandeService {
    @Autowired
    private CommandeRepository commandeRepository;

    public Commandes create(Commandes commandes){
        return commandeRepository.save(commandes);
    }
    public Iterable<Commandes> getAll(){
        return commandeRepository.findAllByIsDeleteIsFalse();
    }
    public Optional<Commandes> getById(Long id){
        return commandeRepository.findById(id);
    }
    public void delete (Commandes deleteObject){
        deleteObject.setDelete(true);
        commandeRepository.save(deleteObject);
    }
    public int modifyState(State articleState, Long id){
        return commandeRepository.modifyState(articleState, id);
    }
}
