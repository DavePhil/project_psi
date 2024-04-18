package com.psi.project_psi.models.ForAdmin;

import com.psi.project_psi.models.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Data
@Entity
@DynamicUpdate
public class IndustryReport extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image1;
    private String image2;
    private String editionYear;
    private String characteristics;
    private String editionNews;
    private String fileExtractForEdition;
    private String filePresseCommunicate;
//    private List<String> listOfPublications;

}
