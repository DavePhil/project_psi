package com.psi.project_psi.service;


import com.psi.project_psi.models.Speciality;
import com.psi.project_psi.repository.SpecialityRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        return specialityRepository.findAllByIsDeleteIsFalse();
    }

    public Optional<Speciality> getById(Long id){
        return specialityRepository.findById(id);
    }

    public void delete (Speciality deleteObject){
        deleteObject.setDelete(true);
        deleteObject.setDeleteAt(new Date());
        specialityRepository.save(deleteObject);
    }

    public void deleteById(Long id){
        specialityRepository.deleteById(id);
    }
}
