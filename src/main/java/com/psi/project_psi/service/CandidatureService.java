package com.psi.project_psi.service;

import com.psi.project_psi.models.Candidature;
import com.psi.project_psi.repository.CandidatureRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class CandidatureService {

    @Autowired
    private CandidatureRepository candidatureRepository;

    public Candidature create(Candidature candidature){
        return candidatureRepository.save(candidature);
    }

    public Iterable<Candidature> getAll(){
        return candidatureRepository.findAll();
    }

    public Optional<Candidature> getById(Long id){
        return candidatureRepository.findById(id);
    }

    public void deleteById(Long id){
        candidatureRepository.deleteById(id);
    }
}
