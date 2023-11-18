package com.psi.project_psi.models;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;

@Data
@Entity
@DynamicUpdate
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String wording;
    private Long minAmount;
    private Long maxAmount;
    private Date delay;
    private String monnaie;
    private Date dateCreation;
    @ManyToOne
    private Domain domain;
    @ManyToOne
    private Ville ville;
    @ManyToOne
    private Pays pays;
    @ManyToOne
    private Users users;

    public Project(){
        this.dateCreation = new Date();
    }

}
