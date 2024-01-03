package com.psi.project_psi.controller.freelance;


import com.psi.project_psi.models.Admin;
import com.psi.project_psi.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Operation(
            description = "Ajouter un administrateur",
            responses = {
                    @ApiResponse(
                            description = "Bad request , if already exists",
                            responseCode = "400 avec un message d'erreur"
                    ),
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    )
            }
    )
    @PostMapping("/admin")
    public ResponseEntity<?> createAdmin(@RequestBody Admin admin){
        if (adminService.getAdmin(admin.getEmail()).isPresent()) return new ResponseEntity<>("Cette utilisateur existe déjà",HttpStatus.BAD_REQUEST);
        Admin saveAdmin = adminService.CreateAdmin(admin); // Recuperation de l'instance à sauvegarder
        return new ResponseEntity<>(saveAdmin, HttpStatus.OK);
    }

    @Operation(
            description = "Login",
            responses = {
                    @ApiResponse(
                            description = "Bad request , if not found",
                            responseCode = "400 avec un message d'erreur"
                    ),
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    )
            }
    )
    @GetMapping("/admin/{email}/{password}")
    public ResponseEntity<?> login(@PathVariable("email") String email , @PathVariable("password") String password){
        Optional<Admin> adminSave = adminService.getAdmin(email); // recuperation de l'admin sauvegardé en base de données
        //Verification si le login est correct, si oui vérifiez si le mot de passe entré par l'utilisateur match avec celui hash en base de données
        if (!adminSave.isPresent()) return new ResponseEntity<>("Identifiant ou mot de passe incorrect", HttpStatus.BAD_REQUEST);
        else if (!adminService.verifyPassword(password, adminSave.get().getPassword())) return new ResponseEntity<>("Identifiant ou mot de passe incorrect", HttpStatus.BAD_REQUEST);
        else return new ResponseEntity<>(adminSave, HttpStatus.OK);
    }

}
