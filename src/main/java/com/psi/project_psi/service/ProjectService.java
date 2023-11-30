package com.psi.project_psi.service;

import com.psi.project_psi.models.Competences;
import com.psi.project_psi.models.Profile;
import com.psi.project_psi.models.Project;
import com.psi.project_psi.repository.ProjectRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    public Project create(Project project){
        return projectRepository.save(project);
    }

    public Iterable<Project> getAll(){
        return projectRepository.findAllByIsDeleteIsFalse();
    }

    public Optional<Project> getById(Long id){
        return projectRepository.findById(id);
    }

    public void deleteById(Long id){
        projectRepository.deleteById(id);
    }

    public void delete (Project deleteObject){
        deleteObject.setDelete(true);
        projectRepository.save(deleteObject);
    }

    public List<Project> findByUser(Long idUser){
        return projectRepository.findProjectByUsers(idUser);
    }

}
