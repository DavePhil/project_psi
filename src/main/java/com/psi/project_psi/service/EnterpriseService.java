package com.psi.project_psi.service;

import com.psi.project_psi.models.Enterprise;
import com.psi.project_psi.repository.EnterpriseRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Data
@Service
public class EnterpriseService {

    @Autowired
    private EnterpriseRepository entrepriseRepository;

    public Enterprise create(Enterprise enterprise){
        return entrepriseRepository.save(enterprise);
    }

    public Iterable<Enterprise> getAll(){
        return entrepriseRepository.findAll();
    }

    public Optional<Enterprise> getById(Long id){
        return entrepriseRepository.findById(id);
    }

    public void deleteById(Long id){
        entrepriseRepository.deleteById(id);
    }
}
