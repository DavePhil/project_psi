package com.psi.project_psi.controller.freelance;

import com.psi.project_psi.models.*;
import com.psi.project_psi.service.ProfileService;
import com.psi.project_psi.service.UserService;
import com.psi.project_psi.utils.Utils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
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
    @Autowired
    private UserService userService;

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
    public ResponseEntity<?> create(@RequestParam("description") String description,
                                    @RequestParam("cv") MultipartFile cv,
                                    @RequestParam("photo") MultipartFile photo,
                                    @RequestParam("user") Users users,
                                    @RequestParam("competences")List<Competences> competences,
                                    @RequestParam(value = "linkedIn", required = false) String linkedIn,
                                    @RequestParam("domain")Domain domain) throws IOException {
        if (Utils.verifyImageExtension(photo)){
            return new ResponseEntity<>("Nous n'acceptions que les images de type jpeg ou alors png", HttpStatus.BAD_REQUEST);
        }
        if (Utils.verifyFileExtensionType(cv)) return new ResponseEntity<>("Nous n'acceptons qu'une version pdf de votre CV", HttpStatus.BAD_REQUEST);
        if (profileService.findByUser(users.getId()).isPresent()) return new ResponseEntity<>("Cette utilisateur a déjà un Profile", HttpStatus.BAD_REQUEST);
        Profile profile = profileService.create(description, cv, photo, users, competences, domain, linkedIn);
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
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        Optional<Profile> deleteObject = profileService.getById(id);
        if (deleteObject.isPresent()) {
            profileService.delete(deleteObject.get());
            return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
        }else return new ResponseEntity<>("Not present", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/profilebydomain/{idDomain}")
    public List<Profile> getByDomain(@PathVariable("idDomain") Long idDomain){
        return profileService.findByDomain(idDomain);
    }
    @PutMapping("/profile/valide/{id}")
    public ResponseEntity<?> valide(@PathVariable("id") Long id){
        Optional<Profile> profile = profileService.getById(id);
        if (profile.isPresent()) return new ResponseEntity<>("Ce profil n'existe pas", HttpStatus.BAD_REQUEST);
        profileService.modifyState(State.Valide,id);
        return new ResponseEntity<>("profil validé", HttpStatus.OK);
    }
    @PutMapping("/profile/rejette/{id}")
    public ResponseEntity<?> rejette(@PathVariable("id") Long id){
        Optional<Profile> profile = profileService.getById(id);
        if (profile.isPresent()) return new ResponseEntity<>("Cet profile n'existe pas", HttpStatus.BAD_REQUEST);
        profileService.modifyState(State.Valide,id);
        return new ResponseEntity<>("profil rejeté", HttpStatus.OK);
    }


//    @GetMapping("/profileUser/{idUser}")
//    public ResponseEntity<?> getProfileByUser(@PathVariable("idUser") Long idUser){
//        List<Profile> profiles = profileService.findByUser(idUser);
//        if (profiles.isEmpty()) return new ResponseEntity<>("Cette utilisateur n'as pas de profil", HttpStatus.OK);
//        return new ResponseEntity<>(profiles, HttpStatus.OK);
//    }
}

