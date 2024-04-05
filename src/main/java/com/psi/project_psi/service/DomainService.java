package com.psi.project_psi.service;

import com.psi.project_psi.models.BankAccount;
import com.psi.project_psi.models.Domain;
import com.psi.project_psi.repository.DomainRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Data
@Service
public class DomainService {

    @Autowired
    private DomainRepository domainRepository;

    public Domain create(Domain domain){
        return domainRepository.save(domain);
    }

    public Iterable<Domain> getAll(){
        return domainRepository.findAllByIsDeleteIsFalse();
    }

    public Optional<Domain> getById(Long id){
        return domainRepository.findById(id);
    }

    public void deleteById(Long id){
        domainRepository.deleteById(id);
    }

    public void delete (Domain deleteObject){
        deleteObject.setDelete(true);
        deleteObject.setDeleteAt(new Date());
        domainRepository.save(deleteObject);
    }
}
