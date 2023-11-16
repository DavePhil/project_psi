package com.psi.project_psi.controller;

import com.psi.project_psi.models.Enterprise;
import com.psi.project_psi.models.EnterpriseTypeIndustry;
import com.psi.project_psi.models.EnterpriseTypeOrganisation;
import com.psi.project_psi.models.Users;
import com.psi.project_psi.service.EnterpriseService;
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
public class EnterpriseController {

    @Autowired
    private EnterpriseService enterpriseService;

    @Operation(
            description = "Ajouter une entreprise",
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
    @PostMapping("/enterprise")
    public ResponseEntity<?> create(@RequestParam("logo")MultipartFile logo,
                                    @RequestParam("banniere") MultipartFile banniere, @RequestParam("name") String name,
                                    @RequestParam("description") String description, @RequestParam("facebookLink") String facebookLink,
                                    @RequestParam("instagramLink") String instagramLink, @RequestParam("linkedLink") String linkedLink,
                                    @RequestParam("twitterLink") String twitterLink, @RequestParam("contactNumber") String contactNumber,
                                    @RequestParam("email") String email, @RequestParam("localisation") String localisation,
                                    @RequestParam("siteWebLink") String siteWebLink, @RequestParam("typeIndustry") EnterpriseTypeIndustry typeIndustry,
                                    @RequestParam("typeOrganisation")EnterpriseTypeOrganisation typeOrganisation, @RequestParam("teamLength") Integer teamLength,
                                    @RequestParam("creationDate") String creationDate, @RequestParam("user")Users user) throws IOException {
        if (!logo.getContentType().equals("image/jpeg") && !logo.getContentType().equals("image/png")){
            return new ResponseEntity<>("Nous n'acceptions que les images de type jpeg ou alors png", HttpStatus.BAD_REQUEST);
        }
        if (!banniere.getContentType().equals("image/jpeg") && !banniere.getContentType().equals("image/png")){
            return new ResponseEntity<>("Nous n'acceptions que les images de type jpeg ou alors png", HttpStatus.BAD_REQUEST);
        }
        Enterprise enterprise = enterpriseService.createEntreprise(logo, banniere, name,description, facebookLink, instagramLink, linkedLink, twitterLink, contactNumber, email, localisation, siteWebLink, typeIndustry,
                typeOrganisation,teamLength, creationDate, user );
        return ResponseEntity.ok(enterprise);
    }

    @PostMapping("/createEnterprise")
    public ResponseEntity<?> create(@RequestParam("logo")MultipartFile logo, @RequestParam("user")Users user,
                                    @RequestParam("banniere") MultipartFile banniere, @RequestParam("name") String name,
                                    @RequestParam("description") String description) throws IOException {
        if (!logo.getContentType().equals("image/jpeg") && !logo.getContentType().equals("image/png")){
            return new ResponseEntity<>("Nous n'acceptions que les images de type jpeg ou alors png", HttpStatus.BAD_REQUEST);
        }
        if (!banniere.getContentType().equals("image/jpeg") && !banniere.getContentType().equals("image/png")){
            return new ResponseEntity<>("Nous n'acceptions que les images de type jpeg ou alors png", HttpStatus.BAD_REQUEST);
        }
        Enterprise enterprise = enterpriseService.createEnterprise(logo, banniere, name,description, user);
        return ResponseEntity.ok(enterprise);
    }
    @PutMapping("/enterprise/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Enterprise enterprise){
        if (!enterpriseService.getById(id).isPresent()) return new ResponseEntity<>("Entreprise absente", HttpStatus.BAD_REQUEST);
        Enterprise enterprise1 = enterpriseService.updateEnterprise(enterprise, id);
        return ResponseEntity.ok(enterprise1);
    }


    @GetMapping("/enterprise/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id ){
        Optional<Enterprise> enterprise = enterpriseService.getById(id);
        if (!enterprise.isPresent()) return new ResponseEntity<>("Cette Entreprise n'existe pas", HttpStatus.OK);
        return new ResponseEntity<>(enterprise, HttpStatus.OK);
    }

    @GetMapping("/enterprises")
    public Iterable<Enterprise> getAll(){
        return enterpriseService.getAll();
    }

    @DeleteMapping("/enterprise/{id}")
    public void delete(@PathVariable("id") Long id){
        enterpriseService.deleteById(id);
    }

    @GetMapping("/entrepriseByUser/{idUser}")
    public ResponseEntity<?> getByIdUser(@PathVariable("idUser") Long idUser){
        List<Enterprise> enterprises = enterpriseService.entrepriseListByUser(idUser);
        if (enterprises.isEmpty()) return new ResponseEntity<>("Aucune entreprise pour cette utilisateur", HttpStatus.OK);
        return new ResponseEntity<>(enterprises, HttpStatus.OK);
    }
}
