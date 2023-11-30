package com.psi.project_psi.service;

import com.psi.project_psi.models.Module;
import com.psi.project_psi.repository.ModuleRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    public Module create(Module module){
        return moduleRepository.save(module);
    }

    public Iterable<Module> getAll(){
        return moduleRepository.findAllByIsDeleteIsFalse();
    }

    public Optional<Module> getById(Long id){
        return moduleRepository.findById(id);
    }

    public void deleteById(Long id){
        moduleRepository.deleteById(id);
    }

    public void delete (Module deleteObject){
        deleteObject.setDelete(true);
        moduleRepository.save(deleteObject);
    }
}
