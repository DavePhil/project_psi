package com.psi.project_psi.models;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;
import java.util.List;

@Data
@Entity
@DynamicUpdate
public class Project extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String wording;
    private Long minAmount;
    private Long maxAmount;
    private Date delay;
    private String monnaie;
    @OneToMany
    private List<Competences> technicalRequirements;
    private String regulatoryCompliance; // Si plusieurs, il m'envoie un string séparé de virgules regulatory
//    private State state = State.EnAttente;
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

