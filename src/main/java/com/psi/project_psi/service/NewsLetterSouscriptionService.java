package com.psi.project_psi.service;


import com.psi.project_psi.models.NewsLetterSouscription;
import com.psi.project_psi.models.Ville;
import com.psi.project_psi.repository.NewsLetterSouscriptionRepository;
import com.psi.project_psi.repository.VilleRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Data
@Service
public class NewsLetterSouscriptionService {

    @Autowired
    private NewsLetterSouscriptionRepository newsLetterSouscriptionRepository;

    public NewsLetterSouscription create(NewsLetterSouscription newsLetterSouscription){
        return newsLetterSouscriptionRepository.save(newsLetterSouscription);
    }

    public Iterable<NewsLetterSouscription> getAll(){
        return newsLetterSouscriptionRepository.findAllByIsDeleteIsFalse();
    }

    public Optional<NewsLetterSouscription> getById(Long id){
        return newsLetterSouscriptionRepository.findById(id);
    }



    public void delete(NewsLetterSouscription deleteObject) {
        deleteObject.setDelete(true);
        deleteObject.setDeleteAt(new Date());
        newsLetterSouscriptionRepository.save(deleteObject);
    }
}
