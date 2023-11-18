package com.psi.project_psi.controller;

import com.psi.project_psi.models.Profile;
import com.psi.project_psi.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Operation(
            description = "Ajouter un profil",
            responses = {
                    @ApiResponse(
                            description = "Bad request , if une erreur se produit(Par exemple sur le type de fichier)",
                            responseCode = "400 avec un message d'erreur"
                    ),
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"

                    )
            }
    )
    @PostMapping("/profile")
    public ResponseEntity<?> create(@RequestParam("name") String name,
                                    @RequestParam("libelle") String libelle,
                                    @RequestParam("description") String description,
                                    @RequestParam("cv") MultipartFile cv,
                                    @RequestParam("photo") MultipartFile photo) throws IOException {
        if (!photo.getContentType().equals("image/jpeg") && !photo.getContentType().equals("image/png")){
            return new ResponseEntity<>("Nous n'acceptions que les images de type jpeg ou alors png", HttpStatus.BAD_REQUEST);
        }
        if (!cv.getContentType().equals("application/pdf")) return new ResponseEntity<>("Nous n'acceptons qu'une version pdf de votre CV", HttpStatus.BAD_REQUEST);
        Profile profile = profileService.create(name, libelle, description, cv, photo);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<?> getById(@PathVariable("id")Long id){
        Optional<Profile> profile = profileService.getById(id);
        if (!profile.isPresent()) return new ResponseEntity<>("Ce profile n'est pas présent", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    @GetMapping("/profiles")
    public Iterable<Profile> getAll(){
        return profileService.getAll();
    }

    @DeleteMapping("/profile")
    public void delete(@PathVariable("id") Long id){
        profileService.deleteById(id);
    }

    @GetMapping("/profileUser/{idUser}")
    public ResponseEntity<?> getProfileByUser(@PathVariable("idUser") Long idUser){
        List<Profile> profiles = profileService.findByUser(idUser);
        if (profiles.isEmpty()) return new ResponseEntity<>("Cette utilisateur n'as pas de profil", HttpStatus.OK);
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }
}

