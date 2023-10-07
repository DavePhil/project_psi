package com.psi.project_psi.service;

import com.psi.project_psi.models.UserFunction;
import com.psi.project_psi.repository.UserFunctionRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class UserFunctionService {

    @Autowired
    private UserFunctionRepository userFunctionRepository;

    public UserFunction createFunction(UserFunction userFunction){
        return userFunctionRepository.save(userFunction);
    }

    public Iterable<UserFunction> getUserFunctions(){
        return userFunctionRepository.findAll();
    }

    public Optional<UserFunction> getUserFunction(Long id){
        return userFunctionRepository.findById(id);
    }

    public void deleteUserFunction(Long id){
        userFunctionRepository.deleteById(id);
    }

}
