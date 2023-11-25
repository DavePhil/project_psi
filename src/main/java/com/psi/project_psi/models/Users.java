package com.psi.project_psi.models;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

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
    @ManyToOne
    private UserFunction userFunction;
    @OneToOne
    private Profile profile;
}
