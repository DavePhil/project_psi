package com.psi.project_psi.service;

import com.psi.project_psi.controller.freelance.AuthentificationResponse;
import com.psi.project_psi.errors.CustomResponseEntity;
import com.psi.project_psi.models.Users;
import com.psi.project_psi.repository.UserRepository;
import com.psi.project_psi.security.JwtService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Data
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final AuthenticationManager authenticationManager;

    public Users CreateUser(Users user){
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        userRepository.save(user);
        return user;
    }

    public AuthentificationResponse register(Users user){
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthentificationResponse.builder()
                .users(user)
                .token(jwtToken)
                .build();
    }

    public ResponseEntity<?> authenticate(Optional<Users> user, String password){
        if (user.isEmpty())  return CustomResponseEntity.fromKey("INVALID_CREDENTIALS", HttpStatus.BAD_REQUEST);
        else if (!verifyPassword(password, user.get().getPassword()))  return CustomResponseEntity.fromKey("INVALID_CREDENTIALS", HttpStatus.BAD_REQUEST);
        var jwtToken = jwtService.generateToken(user.get());
        AuthentificationResponse response = AuthentificationResponse.builder()
                .users(user.get())
                .token(jwtToken)
                .build();
        return ResponseEntity.ok(response);
    }

    public Boolean verifyPassword(String password, String passwordHache){
        return passwordEncoder.matches(password,passwordHache);
    }

    public Optional<Users> getUser(String email){
        return userRepository.findByEmail(email);
    }

    public Optional<Users> getById(Long id){
        return userRepository.findById(id);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public List<Users> findAll(){return userRepository.findAllByIsDeleteIsFalse();}

    public Users update(Users users, Long id){
        Users current = getById(id).get();
        if (users.getUserFunction()!=null) current.setUserFunction(users.getUserFunction());
        if (users.getUserName()!=null) current.setUserName(users.getUserName());
        if (users.getPassword()!=null) current.setPassword(users.getPassword());
        if (users.getEmail()!=null) current.setEmail(users.getEmail());
        if (users.getProfile()!=null) current.setProfile(users.getProfile());
        return userRepository.save(users);
    }


    public void save(Users user) {
        user.setUpdateAt(new Date());
        userRepository.save(user);
    }
}
