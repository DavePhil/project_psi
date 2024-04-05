package com.psi.project_psi.service;

import com.psi.project_psi.models.Actualite;
import com.psi.project_psi.models.Admin;
import com.psi.project_psi.models.NewsLetterSouscription;
import com.psi.project_psi.repository.ActualiteRepository;
import com.psi.project_psi.utils.Utils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
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
    public Actualite create(String titre, String description, MultipartFile image, Admin admin) throws IOException {
        Actualite actualite = new Actualite();
        actualite.setAdmin(admin);
        actualite.setTitle(titre);
        actualite.setDescription(description);
        String _photo = Utils.addMultiPartFile("photo",image);
        actualite.setImage(_photo);
        Iterable<NewsLetterSouscription> souscriptions =  newsLetterSouscriptionService.getAll();
        for (NewsLetterSouscription souscription : souscriptions){
            mailService.sendActualite(souscription.getEmail(),description);
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
        deleteObject.setDeleteAt(new Date());
        newsLetterRepository.save(deleteObject);
    }

}
