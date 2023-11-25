package com.psi.project_psi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Data
@Entity
@DynamicUpdate
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String wording; // wording is libellé in French
    private String description;
    private String curriculumVitae;
    private String photo;
    @JsonIgnore
    @OneToOne
    private Users users;
}
