package com.psi.project_psi.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

@Data
@Entity
@DynamicUpdate
public class Candidature extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private State state = State.EnAttente;
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Module module;
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Profile profile;


}
