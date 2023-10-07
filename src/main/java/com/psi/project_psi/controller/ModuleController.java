package com.psi.project_psi.controller;

import com.psi.project_psi.models.Module;
import com.psi.project_psi.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        if (!module.isPresent()) return new ResponseEntity<>("Ce module n'est pas présent", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(module, HttpStatus.OK);
    }

    @GetMapping("/modules")
    public Iterable<Module> getAll(){
        return moduleService.getAll();
    }

    @DeleteMapping("/module/{id}")
    public void delete(@PathVariable("id") Long id){
        moduleService.deleteById(id);
    }
}
