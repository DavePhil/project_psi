package com.psi.project_psi;

import com.psi.project_psi.models.Role;
import com.psi.project_psi.models.Users;
import com.psi.project_psi.service.UserService;
import com.psi.project_psi.utils.file.FileStorageImpl;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ProjectPsiApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ProjectPsiApplication.class, args);
    }
    @Resource
    FileStorageImpl storageService;
    @Autowired
    UserService userService;
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override   
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("*")
                        .allowedHeaders("*");
            }
        };
    }

    @Override
    public void run(String... arg) throws Exception {
        if (!userService.adminExist()) {
            // Si l'administrateur n'existe pas, créez-le
            Users admin = new Users();
            admin.setRole(Role.SUPER_ADMIN);
            admin.setPassword("admin#pwd0");
            userService.CreateUser(admin);
        }
        storageService.init();
    }

}
