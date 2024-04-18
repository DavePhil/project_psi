package com.psi.project_psi.service;

import com.psi.project_psi.models.EnterpriseTypeIndustry;
import com.psi.project_psi.models.IndustryExperience;
import com.psi.project_psi.repository.EnterpriseTypeIndustryRepository;
import com.psi.project_psi.repository.IndustryExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class IndustryExperienceService {
    @Autowired
    private IndustryExperienceRepository repository;

    public IndustryExperience create(IndustryExperience industryExperience){
        return repository.save(industryExperience);
    }

    public Iterable<IndustryExperience> getAll(){
        return repository.findAllByIsDeleteIsFalse();
    }

    public Optional<IndustryExperience> getById(Long id){
        return repository.findById(id);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public void delete (IndustryExperience deleteObject){
        deleteObject.setDelete(true);
        deleteObject.setDeleteAt(new Date());
        repository.save(deleteObject);
    }
}
