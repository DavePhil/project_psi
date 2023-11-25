package com.psi.project_psi.service;

import com.psi.project_psi.models.Profile;
import com.psi.project_psi.models.Users;
import com.psi.project_psi.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    public Object getUserProfile(Long userId){
        return userRepository.userProfile(userId);
    }
    public Optional<Users> getById(Long id){
        return userRepository.findById(id);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public Users update(Users users, Long id){
        Users current = getById(id).get();
        if (users.getUserFunction()!=null) current.setUserFunction(users.getUserFunction());
        if (users.getUserName()!=null) current.setUserName(users.getUserName());
        if (users.getPassword()!=null) current.setPassword(users.getPassword());
        if (users.getEmail()!=null) current.setEmail(users.getEmail());
        if (users.getProfile()!=null) current.setProfile(users.getProfile());
        return userRepository.save(users);
    }

    public void updateProfile(Profile profile, Users users){
        if (profile!=null) users.setProfile(profile);
        userRepository.save(users);
    }
}
