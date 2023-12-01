package com.psi.project_psi.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

@Data
@Entity
@DynamicUpdate
public class Candidature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean isAccept = false; // candidature acceptée pour le module ?
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Module module;
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Profile profile;
    private boolean isDelete = false;

}
