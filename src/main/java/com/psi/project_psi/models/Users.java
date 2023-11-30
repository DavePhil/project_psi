package com.psi.project_psi.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Data
@Entity
@DynamicUpdate
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    @Column(unique = true)
    private String email;
    private String password;
    private Integer age;
    private String localisation;
    @ManyToOne
    private UserFunction userFunction;
    @OneToOne(mappedBy = "users")
    private Profile profile;
    // Rajouter la contrainte d'intégrité qui supprime tous les bankAccounts si l'utilisateur est supprimé
    private boolean isDelete = false;
}
