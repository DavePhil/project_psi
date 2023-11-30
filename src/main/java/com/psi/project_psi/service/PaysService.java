package com.psi.project_psi.service;


import com.psi.project_psi.models.Pays;
import com.psi.project_psi.repository.PaysRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class PaysService {

    @Autowired
    private PaysRepository paysRepository;

    public Pays create(Pays pays){
        return paysRepository.save(pays);
    }

    public Iterable<Pays> getAll(){
        return paysRepository.findAllByIsDeleteIsFalse();
    }

    public Optional<Pays> getById(Long id){
        return paysRepository.findById(id);
    }

    public void deleteById(Long id){
        paysRepository.deleteById(id);
    }

    public void delete (Pays deleteObject){
        deleteObject.setDelete(true);
        paysRepository.save(deleteObject);
    }
}
