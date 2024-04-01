package com.psi.project_psi.controller.freelance;


import com.psi.project_psi.errors.CustomResponseEntity;
import com.psi.project_psi.models.UserFunction;
import com.psi.project_psi.models.Users;
import com.psi.project_psi.service.UserFunctionService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserFunctionController {

    @Autowired
    private UserFunctionService userFunctionService;

    @PostMapping("/function")
    public ResponseEntity<?> createUserFunction(@RequestBody UserFunction userFunction){
        UserFunction userFunctionSave = userFunctionService.createFunction(userFunction);
        return new ResponseEntity<>(userFunctionSave, HttpStatus.OK);
    }

    @GetMapping("/functions")
    public Iterable<UserFunction> allFunction(){
        return userFunctionService.getUserFunctions();
    }

    @GetMapping("/function/{id}")
    public ResponseEntity<?> functionById(@PathVariable("id") Long id){
        Optional<UserFunction> userFunction = userFunctionService.getUserFunction(id);
        if (userFunction.isEmpty()) return CustomResponseEntity.fromKey("RESSOURCE_INTROUVABLE", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(userFunction, HttpStatus.OK);
    }

    @DeleteMapping("/function/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        Optional<UserFunction> deleteObject = userFunctionService.getUserFunction(id);
        if (deleteObject.isPresent()) {
            userFunctionService.delete(deleteObject.get());
            return CustomResponseEntity.fromKey("DELETE_SUCCESSFULLY", HttpStatus.OK);
        }else return CustomResponseEntity.fromKey("RESSOURCE_INTROUVABLE", HttpStatus.BAD_REQUEST);
    }

}
