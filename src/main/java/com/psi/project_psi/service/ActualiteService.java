package com.psi.project_psi.service;

import com.psi.project_psi.models.Actualite;
import com.psi.project_psi.models.NewsLetterSouscription;
import com.psi.project_psi.repository.ActualiteRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class ActualiteService {

    @Autowired
    ActualiteRepository newsLetterRepository;
    @Autowired
    NewsLetterSouscriptionService newsLetterSouscriptionService;
    @Autowired
    MailService mailService;

    public Actualite create(Actualite actualite){
        Iterable<NewsLetterSouscription> souscriptions =  newsLetterSouscriptionService.getAll();
        for (NewsLetterSouscription souscription : souscriptions){
            mailService.sendActualite(souscription.getEmail(),actualite.getDescription());
        }
        return newsLetterRepository.save(actualite);
    }

    public Iterable<Actualite> getAll(){
        return newsLetterRepository.findAllByIsDeleteIsFalse();
    }

    public Optional<Actualite> getById(Long id){
        return newsLetterRepository.findById(id);
    }

    public void deleteById(Long id){
        newsLetterRepository.deleteById(id);
    }

    public void delete (Actualite deleteObject){
        deleteObject.setDelete(true);
        newsLetterRepository.save(deleteObject);
    }

}
