package com.psi.project_psi.service;

import com.psi.project_psi.models.Competences;
import com.psi.project_psi.repository.CompetencesRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class CompetencesService {

    @Autowired
    private CompetencesRepository competencesRepository;

    public Competences create(Competences competences){
        return competencesRepository.save(competences);
    }

    public Iterable<Competences> getAll(){
        return competencesRepository.findAll();
    }

    public Optional<Competences> getById(Long id){return competencesRepository.findById(id);}

    public List<Competences> findByDomain(Long idDomain){
        return competencesRepository.findCompetencesByDomain(idDomain);
    }

    public void deleteById(Long id){
        competencesRepository.deleteById(id);
    }
}
