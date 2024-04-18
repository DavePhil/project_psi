package com.psi.project_psi.models.ForAdmin;

import com.psi.project_psi.models.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

@DynamicUpdate
@Entity
@Data
public class Evenement extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String datePublication;
    private String bigTitle;
    private String sousTitle;
    private String image;
    private String description;

}
