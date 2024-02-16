package com.psi.project_psi.models;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Data
@Entity
@DynamicUpdate
public class Actualite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String image;
    private Date dateCreation = new Date();
    private boolean isDelete = false;
    @ManyToOne
    private Admin admin;

}
