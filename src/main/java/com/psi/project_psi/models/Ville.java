package com.psi.project_psi.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.Remove;
import org.hibernate.annotations.DynamicUpdate;

@Data
@Entity
public class Ville {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    @ManyToOne
    private Pays pays;
    private boolean isDelete = false;

}
