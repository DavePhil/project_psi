package com.psi.project_psi.service;


import com.psi.project_psi.models.Speciality;
import com.psi.project_psi.repository.SpecialityRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class SpecialityService {

    @Autowired
    private SpecialityRepository specialityRepository;

    public Speciality create(Speciality speciality){
        return specialityRepository.save(speciality);
    }

    public Iterable<Speciality> getAll(){
        return specialityRepository.findAll();
    }

    public Optional<Speciality> getById(Long id){
        return specialityRepository.findById(id);
    }

    public void deleteById(Long id){
        specialityRepository.deleteById(id);
    }
}
