package com.psi.project_psi.controller.freelance;

import com.psi.project_psi.errors.CustomResponseEntity;
import com.psi.project_psi.models.Module;
import com.psi.project_psi.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @PostMapping("/module")
    public Module create(@RequestBody Module module){
        return moduleService.create(module);
    }

    @GetMapping("/module/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        Optional<Module> module = moduleService.getById(id);
        if (module.isEmpty()) return CustomResponseEntity.fromKey("RESSOURCE_INTROUVABLE", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(module, HttpStatus.OK);
    }

    @GetMapping("/modules")
    public Iterable<Module> getAll(){
        return moduleService.getAll();
    }

    @GetMapping("/modulesByProject/{idProject}")
    public List<Module> getByProject(@PathVariable("idProject") Long idProject){
        return moduleService.moduleByProject(idProject);
    }

    @DeleteMapping("/module/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        Optional<Module> deleteObject = moduleService.getById(id);
        if (deleteObject.isPresent()) {
            moduleService.delete(deleteObject.get());
            return CustomResponseEntity.fromKey("DELETE_SUCCESSFULLY", HttpStatus.OK);
        }else return CustomResponseEntity.fromKey("RESSOURCE_INTROUVABLE", HttpStatus.BAD_REQUEST);
    }
}
