package com.psi.project_psi.service;

import com.psi.project_psi.models.Certifications;
import com.psi.project_psi.repository.CertificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class CertificationService {
    @Autowired
    private CertificationRepository repository;

    public Certifications create(Certifications certifications){
        return repository.save(certifications);
    }

    public Iterable<Certifications> getAll(){
        return repository.findAllByIsDeleteIsFalse();
    }

    public Optional<Certifications> getById(Long id){
        return repository.findById(id);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public void delete (Certifications deleteObject){
        deleteObject.setDelete(true);
        deleteObject.setDeleteAt(new Date());
        repository.save(deleteObject);
    }
}
