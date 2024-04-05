package com.psi.project_psi.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

@Data
@Entity
@DynamicUpdate
public class Enterprise extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Logo;
    private String banniere;
    private String name;
    private String description;
    private String facebookLink;
    private String instagramLink;
    private String linkedinLink;
    private String twitterLink;
    private String contactNumber;
    private String email;
    private String localisation;
    private String siteWebLink;
    @ManyToOne
    private EnterpriseTypeIndustry typeIndustry;
    @ManyToOne
    private EnterpriseTypeOrganisation typeOrganisation;
    private Integer teamLength;
    private String creationDate;
    @ManyToOne
    private Users users;

}
