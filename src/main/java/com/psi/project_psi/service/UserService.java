package com.psi.project_psi.service;

import com.psi.project_psi.models.Users;
import com.psi.project_psi.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);

    public Users CreateUser(Users user){
        String encodePassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        userRepository.save(user);
        return user;
    }

    public Boolean verifyPassword(String password, String passwordHaché){
        return bCryptPasswordEncoder.matches(password,passwordHaché);
    }

    public Optional<Users> getUser(String email){
        return userRepository.findByEmail(email);
    }

    public Users getUserByEmailAndPassword(String email, String password){
        return userRepository.findByEmailAndPassword(email, password);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
