package com.psi.project_psi.controller.freelance;


import com.psi.project_psi.errors.CustomResponseEntity;
import com.psi.project_psi.models.Project;
import com.psi.project_psi.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/project")
    public Project create(@RequestBody Project project){
        return projectService.create(project);
    }

    @GetMapping("/project/{id}")
    public ResponseEntity<?> getById (@PathVariable("id") Long id){
        Optional<Project> project = projectService.getById(id);
        if (project.isEmpty()) return CustomResponseEntity.fromKey("RESSOURCE_INTROUVABLE", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @GetMapping("/projects")
    public Iterable<Project> getAll (){
        return projectService.getAll();
    }
    @DeleteMapping("/project/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        Optional<Project> deleteObject = projectService.getById(id);
        if (deleteObject.isPresent()) {
            projectService.delete(deleteObject.get());
            return CustomResponseEntity.fromKey("DELETE_SUCCESSFULLY", HttpStatus.OK);
        }else return CustomResponseEntity.fromKey("RESSOURCE_INTROUVABLE", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/projectUser/{idUser}")
    public ResponseEntity<?> getProjectByUser(@PathVariable("idUser") Long idUser){
        List<Project> projectList = projectService.findByUser(idUser);
        if (projectList.isEmpty()) return CustomResponseEntity.fromKey("RESSOURCE_INTROUVABLE", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(projectList, HttpStatus.OK);
    }
}
