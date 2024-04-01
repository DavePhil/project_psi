package com.psi.project_psi.service;


import com.psi.project_psi.models.Admin;
import com.psi.project_psi.repository.AdminRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);

    public Admin CreateAdmin(Admin admin){
        String encodePassword = bCryptPasswordEncoder.encode(admin.getPassword());
        admin.setPassword(encodePassword);
        adminRepository.save(admin);
        return admin;
    }

    public Boolean verifyPassword(String password, String passwordHaché){
        return bCryptPasswordEncoder.matches(password,passwordHaché);
    }
    public Optional<Admin> getAdmin(String email){
        return adminRepository.findAdminByEmail(email);
    }

    public Optional<Admin> getAdminByPasswordAndEmail(String email, String password){
        return adminRepository.findAdminByEmailAndPassword(email, password);
    }

    public void delete (Admin admin){
        admin.setDelete(true);
        adminRepository.save(admin);
    }


}
