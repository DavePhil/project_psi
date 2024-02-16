package com.psi.project_psi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Entity
@DynamicUpdate
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String curriculumVitae;
    private String photo;
    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Users users;
    @ManyToMany
    private List<Competences> competences;
    private String LinkedInLink;
    @ManyToOne
    private Domain domain;
    private State state=State.EnAttente;
    private boolean isDelete = false;
}
