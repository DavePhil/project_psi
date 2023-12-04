package com.psi.project_psi.service;

import com.psi.project_psi.models.Candidature;
import com.psi.project_psi.models.State;
import com.psi.project_psi.repository.CandidatureRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
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
        return candidatureRepository.findAllByIsDeleteIsFalse();
    }

    public Optional<Candidature> getById(Long id){
        return candidatureRepository.findById(id);
    }

    public void deleteById(Long id){
        candidatureRepository.deleteById(id);
    }

    public void delete (Candidature deleteObject){
        deleteObject.setDelete(true);
        candidatureRepository.save(deleteObject);
    }
    public ResponseEntity<?> changeState(State state, Long id){
        Optional<Candidature> candidature = getById(id);
        if (!candidature.isPresent()) return new ResponseEntity<>("Cette candidature n'est pas présente", HttpStatus.BAD_REQUEST);
        int done = candidatureRepository.modifyState(state,id);
        if (done!=0) return new ResponseEntity<>("Candidature modifiée", HttpStatus.OK);
        else return new ResponseEntity<>("Cette candidature n'a pas été validé", HttpStatus.BAD_REQUEST);
    }

    public List<Candidature> getByState(State state){
        return candidatureRepository.getCandidatureByState(state);
    }

}
