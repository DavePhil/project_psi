package com.psi.project_psi.controller.freelance;

import com.psi.project_psi.errors.CustomResponseEntity;
import com.psi.project_psi.models.Profile;
import com.psi.project_psi.models.Role;
import com.psi.project_psi.models.Users;
import com.psi.project_psi.service.UserService;
import com.psi.project_psi.utils.Utils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(
            description = "Ajouter un user ou signup",
            responses = {
                    @ApiResponse(
                            description = "Bad request , if already exists",
                            responseCode = "400 avec message d'erreur",
                            content = {
                                    @Content(
                                            examples = {
                                                    @ExampleObject(
                                                            value = "Cette utilisateur existe déjà"
                                                    )
                                            }
                                    )
                            }
                    ),
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            examples = {
                                                    @ExampleObject(
                                                            value = "\"id\": 4,\n" +
                                                                    "    \"email\": \"davecheddjoun@gmail.com\",\n" +
                                                                    "    \"password\": \"$2a$10$VAzCAeoDc4Q3SazEFz1AZOuJz5c/HWzABMmzojYi.M3Wt/Ah3Mraq\",\n" +
                                                                    "    \"userFunction\": {\n" +
                                                                    "        \"id\": 1,\n" +
                                                                    "        \"name\": null\n" +
                                                                    "    }  "
                                                    )
                                            }
                                    )
                            }
                    )
            }
    )
    @PostMapping("/user")
    public ResponseEntity<?> create(@RequestBody Users user){
        if (userService.getUser(user.getEmail()).isPresent()) return CustomResponseEntity.fromKey("USER_ALREADY_EXISTS", HttpStatus.BAD_REQUEST);
        Users saveUser = userService.CreateUser(user); // Recuperation de l'instance à sauvegarder
        return new ResponseEntity<>(saveUser, HttpStatus.OK);
    }

    @Operation(
            description = "login",
            responses = {
                    @ApiResponse(
                            description = "Bad request , if not found",
                            responseCode = "400"
                    ),
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    )
            }
    )
    @GetMapping("/user/{email}/{password}")
    public ResponseEntity<?> login(@PathVariable("email") String email, @PathVariable("password") String password ){
        Optional<Users> userSave = userService.getUser(email); // recuperation de l'utilisateur sauvegardé en base de données
        //Verification si le login est correct, si oui vérifiez si le mot de passe entré par l'utilisateur match avec celui hash en base de données
        if (userSave.isEmpty()) return CustomResponseEntity.fromKey("INVALID_CREDENTIALS", HttpStatus.BAD_REQUEST);
        else if (!userService.verifyPassword(password, userSave.get().getPassword())) return CustomResponseEntity.fromKey("INVALID_CREDENTIALS", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(userSave, HttpStatus.OK);
    }

    @PostMapping("auth/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthentificationRequest request){
        Optional<Users> userSave = userService.getUser(request.getEmail()); // recuperation de l'utilisateur sauvegardé en base de données
        return userService.authenticate(userSave, request.getPassword());
    }

    @PostMapping("auth/register")
    public ResponseEntity<?> register(@RequestBody Users users){
        if (userService.getUser(users.getEmail()).isPresent()) return CustomResponseEntity.fromKey("USER_ALREADY_EXISTS", HttpStatus.BAD_REQUEST);
        users.setRole(Role.USER);
        return ResponseEntity.ok(userService.register(users));
    }

    @PostMapping("/registerAdmin")
    public ResponseEntity<?> registerAdmin(@RequestBody Users users){
        if (userService.getUser(users.getEmail()).isPresent()) return CustomResponseEntity.fromKey("USER_ALREADY_EXISTS", HttpStatus.BAD_REQUEST);
        users.setRole(Role.ADMIN);
        return ResponseEntity.ok(userService.register(users));
    }

    @GetMapping("/users")
    public List<Users> getAllUsers(){
        return userService.findAll();
    }
}
