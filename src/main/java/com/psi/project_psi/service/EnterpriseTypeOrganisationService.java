package com.psi.project_psi.service;

import com.psi.project_psi.models.EnterpriseTypeOrganisation;
import com.psi.project_psi.repository.EnterpriseTypeOrganisationRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Data
public class EnterpriseTypeOrganisationService {

    @Autowired
    private EnterpriseTypeOrganisationRepository organisationRepository;

    public EnterpriseTypeOrganisation create(EnterpriseTypeOrganisation typeOrganisation){
        return organisationRepository.save(typeOrganisation);
    }

    public Iterable<EnterpriseTypeOrganisation> getAll(){
        return organisationRepository.findAll();
    }

    public Optional<EnterpriseTypeOrganisation> getById(Long id){
        return organisationRepository.findById(id);
    }

    public void deleteById(Long id){
        organisationRepository.deleteById(id);
    }
}
