package com.psi.project_psi.service;


import com.psi.project_psi.models.*;
import com.psi.project_psi.repository.ProfileRepository;
import com.psi.project_psi.utils.Utils;
import com.psi.project_psi.utils.file.FileStorageImpl;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Data
@Service
public class ProfileService {

    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    FileStorageImpl fileStorage;

    public Profile create(Profile profile){
        return profileRepository.save(profile);
    }

    public Iterable<Profile> getAll(){
        return profileRepository.findAllByIsDeleteIsFalse();
    }

    public Optional<Profile> getById(Long id){
        return profileRepository.findById(id);
    }

    public void deleteById(Long id){
        profileRepository.deleteById(id);
    }

    public Profile create(String description, MultipartFile cv, MultipartFile photo, Users users, List<Competences> competences, Domain domain, String linkedIn) throws IOException {
        Profile profile = new Profile();
        String _photo = fileStorage.save("photo",photo);
        String _cv = fileStorage.save("cv",cv);
        profile.setCurriculumVitae(_cv);
        profile.setDescription(description);
        profile.setUsers(users);
        profile.setDomain(domain);
        profile.setLinkedInLink(linkedIn);
        profile.setCompetences(competences);
        profile.setPhoto(_photo);
        profileRepository.save(profile);
        return profile;
    }
    public List<Profile> findByDomain(Long idDomain){
        return profileRepository.findProfileByDomain(idDomain);
    }

    public Optional<Profile> findByUser(Long idUser){
        return profileRepository.findProfileByUsers(idUser);
    }

    public void delete (Profile deleteObject){
        deleteObject.setDelete(true);
        profileRepository.save(deleteObject);
    }
    public int modifyState(State articleState, Long id){
        return profileRepository.modifyState(articleState, id);
    }
}
