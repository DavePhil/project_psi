package com.psi.project_psi.controller;


import com.psi.project_psi.models.UserFunction;
import com.psi.project_psi.service.UserFunctionService;
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
        if (!userFunction.isPresent()) return new ResponseEntity<>("Cette fonction n'existe pas", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(userFunction, HttpStatus.OK);
    }

    @DeleteMapping("/function/{id}")
    public void delete(@PathVariable("id") Long id){
        userFunctionService.deleteUserFunction(id);
    }

}
