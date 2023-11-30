package com.psi.project_psi.service;

import com.psi.project_psi.models.Tasks;
import com.psi.project_psi.repository.TasksRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class TasksService {
    @Autowired
    private TasksRepository tasksRepository;

    public Tasks create(Tasks competences){
        return tasksRepository.save(competences);
    }

    public Iterable<Tasks> getAll(){
        return tasksRepository.findAllByIsDeleteIsFalse();
    }

    public Optional<Tasks> getById(Long id){return tasksRepository.findById(id);}

    public List<Tasks> findByModule(Long idDomain){
        return tasksRepository.findTasksByModule(idDomain);
    }

    public void deleteById(Long id){
        tasksRepository.deleteById(id);
    }

    public void delete (Tasks deleteObject){
        deleteObject.setDelete(true);
        tasksRepository.save(deleteObject);
    }
}
