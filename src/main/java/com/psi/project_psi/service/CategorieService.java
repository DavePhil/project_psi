package com.psi.project_psi.service;

import com.psi.project_psi.models.Categorie;
import com.psi.project_psi.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.Optional;

@Service
public class CategorieService {
    @Autowired
    private CategorieRepository categorieRepository;
    public Categorie create(Categorie categorie){
        return categorieRepository.save(categorie);
    }

    public Iterable<Categorie> getAll(){
        return categorieRepository.findAllByIsDeleteIsFalse();
    }

    public Optional<Categorie> getById(Long id){
        return categorieRepository.findById(id);
    }

    public void delete (Categorie deleteObject){
        deleteObject.setDelete(true);
        deleteObject.setDeleteAt(new Date());
        categorieRepository.save(deleteObject);
    }
}
