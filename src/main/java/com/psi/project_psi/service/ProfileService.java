package com.psi.project_psi.service;


import com.psi.project_psi.models.Profile;
import com.psi.project_psi.repository.ProfileRepository;
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

    public Profile create(Profile profile){
        return profileRepository.save(profile);
    }

    public Iterable<Profile> getAll(){
        return profileRepository.findAll();
    }

    public Optional<Profile> getById(Long id){
        return profileRepository.findById(id);
    }

    public void deleteById(Long id){
        profileRepository.deleteById(id);
    }

    public Profile create(String name, String libelle, String description, MultipartFile cv, MultipartFile photo) throws IOException {
        Profile profile = new Profile();
        final String folderPhoto = new ClassPathResource("static/photo").getFile().getAbsolutePath();
        final String folderCv = new ClassPathResource("static/cv").getFile().getAbsolutePath();
        final String routePhoto = ServletUriComponentsBuilder.fromCurrentContextPath().path("/photo/").path(photo.getOriginalFilename()).toUriString();
        final String routeCv = ServletUriComponentsBuilder.fromCurrentContextPath().path("/cv/").path(photo.getOriginalFilename()).toUriString();
        byte [] bytesPhoto = photo.getBytes();
        byte [] bytesCv = cv.getBytes();
        Path pathPhoto = Paths.get(folderPhoto + File.separator + photo.getOriginalFilename());
        Path pathCv = Paths.get(folderCv + File.separator + cv.getOriginalFilename());
        Files.write(pathPhoto, bytesPhoto);
        Files.write(pathCv, bytesCv);
        System.out.println(routeCv);
        System.out.println(routePhoto);
        profile.setCurriculumVitae("/cv/"+ cv.getOriginalFilename());
        profile.setName(name);
        profile.setDescription(description);
        profile.setWording(libelle);
        profile.setPhoto("/photo/"+photo.getOriginalFilename());
        profileRepository.save(profile);
        return profile;
    }

    public List<Profile> findByUser(Long idUser){
        return profileRepository.findProfileByUsers(idUser);
    }
}
