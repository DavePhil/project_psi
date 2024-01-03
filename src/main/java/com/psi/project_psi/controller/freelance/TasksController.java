package com.psi.project_psi.controller.freelance;


import com.psi.project_psi.models.Tasks;
import com.psi.project_psi.service.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TasksController {
    @Autowired
    private TasksService tasksService;

    @PostMapping("/tasks")
    public Tasks create(@RequestBody Tasks competence){
        return tasksService.create(competence);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        Optional<Tasks> competences = tasksService.getById(id);
        if (!competences.isPresent()) return new ResponseEntity<>("Cette compétence n'est pas présente", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(competences, HttpStatus.OK);
    }

    @GetMapping("/tasks")
    public Iterable<Tasks> getAll(){
        return tasksService.getAll();
    }

    @GetMapping("/tasksbymodule/{idModule}")
    public List<Tasks> getByModule(@PathVariable("idModule") Long idModule){
        return tasksService.findByModule(idModule);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        Optional<Tasks> deleteObject = tasksService.getById(id);
        if (deleteObject.isPresent()) {
            tasksService.delete(deleteObject.get());
            return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
        }else return new ResponseEntity<>("Not present", HttpStatus.BAD_REQUEST);
    }
}
