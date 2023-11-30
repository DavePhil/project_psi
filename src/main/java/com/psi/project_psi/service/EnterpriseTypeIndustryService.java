package com.psi.project_psi.service;

import com.psi.project_psi.models.EnterpriseTypeIndustry;
import com.psi.project_psi.repository.EnterpriseTypeIndustryRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Data
public class EnterpriseTypeIndustryService {
    @Autowired
    private EnterpriseTypeIndustryRepository industryRepository;

    public EnterpriseTypeIndustry create(EnterpriseTypeIndustry typeIndustry){
        return industryRepository.save(typeIndustry);
    }

    public Iterable<EnterpriseTypeIndustry> getAll(){
        return industryRepository.findAllByIsDeleteIsFalse();
    }

    public Optional<EnterpriseTypeIndustry> getById(Long id){
        return industryRepository.findById(id);
    }

    public void deleteById(Long id){
        industryRepository.deleteById(id);
    }

    public void delete (EnterpriseTypeIndustry deleteObject){
        deleteObject.setDelete(true);
        industryRepository.save(deleteObject);
    }
}
