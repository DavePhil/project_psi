package com.psi.project_psi.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

@Data
@Entity
@DynamicUpdate
public class Article extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    @ManyToOne
    private Categorie categorie;
    private String photo;
    private String description;
    private Long prix;
    private State state=State.EnAttente; // En attente de l'approbation d'un admin
    private boolean isDelete = false;
    @ManyToOne
    private Users users;
}
