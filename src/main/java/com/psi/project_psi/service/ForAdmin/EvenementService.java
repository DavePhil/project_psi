package com.psi.project_psi.service.ForAdmin;

import com.psi.project_psi.models.ForAdmin.Evenement;
import com.psi.project_psi.repository.ForAdmin.EvenementRepository;
import com.psi.project_psi.utils.file.FileStorageImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@Service
public class EvenementService {
    @Autowired
    private EvenementRepository repository;
    @Autowired
    FileStorageImpl fileStorage;
    public Iterable<Evenement> getAll(){
        return repository.findAllByIsDeleteIsFalse();
    }
    public Optional<Evenement> getById(Long id){
        return repository.findById(id);
    }
    public void delete (Evenement deleteObject){
        deleteObject.setDelete(true);
        deleteObject.setDeleteAt(new Date());
        repository.save(deleteObject);
    }
    public Evenement create(MultipartFile image, String publicationDate, String bigTitle, String sousTitle, String description) throws IOException {
        Evenement evenement = new Evenement();
        String _image = fileStorage.save("photoEvenement", image);
        evenement.setDescription(description);
        evenement.setImage(_image);
        evenement.setBigTitle(bigTitle);
        evenement.setSousTitle(sousTitle);
        evenement.setDatePublication(publicationDate);
        return repository.save(evenement);
    }
}
