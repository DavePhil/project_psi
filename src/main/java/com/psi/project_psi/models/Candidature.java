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
    private boolean isAccept; // candidature acceptée pour le module ?
    @OneToOne
    private Module module;
    @OneToOne
    private Profile profile;
    private boolean isDelete = false;

}
