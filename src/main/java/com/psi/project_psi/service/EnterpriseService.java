package com.psi.project_psi.service;

import com.psi.project_psi.models.Enterprise;
import com.psi.project_psi.models.EnterpriseTypeIndustry;
import com.psi.project_psi.models.EnterpriseTypeOrganisation;
import com.psi.project_psi.models.Users;
import com.psi.project_psi.repository.EnterpriseRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Optional;


@Data
@Service
public class EnterpriseService {

    @Autowired
    private EnterpriseRepository entrepriseRepository;

    public Enterprise create(Enterprise enterprise){
        return entrepriseRepository.save(enterprise);
    }

    public Iterable<Enterprise> getAll(){
        return entrepriseRepository.findAllByIsDeleteIsFalse();
    }

    public Optional<Enterprise> getById(Long id){
        return entrepriseRepository.findById(id);
    }

    public void deleteById(Long id){
        entrepriseRepository.deleteById(id);
    }

    public Enterprise createEntreprise(MultipartFile logo, MultipartFile banniere, String name, String description, String facebookLink,
                                        String instagramLink, String linkedLink, String twitterLink, String contactNumber, String email,
                                        String localisation, String siteWebLink, EnterpriseTypeIndustry typeIndustry, EnterpriseTypeOrganisation typeOrganisation,
                                        Integer teamLength, String creationDate, Users user) throws IOException {
        Enterprise enterprise =  new Enterprise();
        final String folderLogo = new ClassPathResource("static/logo").getFile().getAbsolutePath();
        final String folderBanniere = new ClassPathResource("static/banniere").getFile().getAbsolutePath();
        final String routeLogo = ServletUriComponentsBuilder.fromCurrentContextPath().path("/logo/").path(logo.getOriginalFilename()).toUriString();
        final String routeBanniere = ServletUriComponentsBuilder.fromCurrentContextPath().path("/banniere/").path(banniere.getOriginalFilename()).toUriString();
        System.out.println(routeBanniere);
        System.out.println(routeLogo);
        byte [] byteLogo = logo.getBytes();
        byte [] byteBanniere = banniere.getBytes();
        Path pathLogo = Paths.get(folderLogo + File.separator + logo.getOriginalFilename());
        Path pathBanniere = Paths.get(folderBanniere + File.separator + banniere.getOriginalFilename());
        Files.write(pathBanniere, byteBanniere);
        Files.write(pathLogo, byteLogo);
        enterprise.setBanniere("/banniere/"+ banniere.getOriginalFilename());
        enterprise.setLogo("/logo/"+ logo.getOriginalFilename());
        enterprise.setCreationDate(creationDate);
        enterprise.setEmail(email);
        enterprise.setUsers(user);
        enterprise.setContactNumber(contactNumber);
        enterprise.setInstagramLink(instagramLink);
        enterprise.setLocalisation(localisation);
        enterprise.setTeamLength(teamLength);
        enterprise.setTypeIndustry(typeIndustry);
        enterprise.setTypeOrganisation(typeOrganisation);
        enterprise.setTwitterLink(twitterLink);
        enterprise.setName(name);
        enterprise.setFacebookLink(facebookLink);
        enterprise.setDescription(description);
        enterprise.setLinkedinLink(linkedLink);
        enterprise.setSiteWebLink(siteWebLink);
        return create(enterprise);
    }

    public Enterprise createEnterprise(MultipartFile logo, MultipartFile banniere, String name, String description, Users user) throws IOException {
        Enterprise enterprise =  new Enterprise();
        final String folderLogo = new ClassPathResource("static/logo").getFile().getAbsolutePath();
        final String folderBanniere = new ClassPathResource("static/banniere").getFile().getAbsolutePath();
        final String routeLogo = ServletUriComponentsBuilder.fromCurrentContextPath().path("/logo/").path(logo.getOriginalFilename()).toUriString();
        final String routeBanniere = ServletUriComponentsBuilder.fromCurrentContextPath().path("/banniere/").path(banniere.getOriginalFilename()).toUriString();
        System.out.println(routeBanniere);
        System.out.println(routeLogo);
        byte [] byteLogo = logo.getBytes();
        byte [] byteBanniere = banniere.getBytes();
        Path pathLogo = Paths.get(folderLogo + File.separator + logo.getOriginalFilename());
        Path pathBanniere = Paths.get(folderBanniere + File.separator + banniere.getOriginalFilename());
        Files.write(pathBanniere, byteBanniere);
        Files.write(pathLogo, byteLogo);
        enterprise.setBanniere("/banniere/"+ banniere.getOriginalFilename());
        enterprise.setLogo("/logo/"+ logo.getOriginalFilename());
        enterprise.setUsers(user);
        enterprise.setDescription(description);
        enterprise.setName(name);
        return create(enterprise);
    }

    public Enterprise updateEnterprise(Enterprise enterprise, Long id){
        Enterprise current = getById(id).get();
        if (enterprise.getLogo()!=null) current.setLogo(enterprise.getLogo());
        if (enterprise.getBanniere()!=null) current.setBanniere(enterprise.getBanniere());
        if (enterprise.getUsers()!=null) current.setUsers(enterprise.getUsers());
        if (enterprise.getName()!=null) current.setName(enterprise.getName());
        if (enterprise.getDescription()!=null) current.setDescription(enterprise.getDescription());
        if (enterprise.getFacebookLink()!=null) current.setFacebookLink(enterprise.getFacebookLink());
        if (enterprise.getInstagramLink()!=null) current.setInstagramLink(enterprise.getInstagramLink());
        if (enterprise.getLinkedinLink()!=null) current.setLinkedinLink(enterprise.getLinkedinLink());
        if (enterprise.getTeamLength()!=null) current.setTeamLength(enterprise.getTeamLength());
        if (enterprise.getTwitterLink()!=null) current.setTwitterLink(enterprise.getTwitterLink());
        if (enterprise.getContactNumber()!=null) current.setContactNumber(enterprise.getContactNumber());
        if (enterprise.getEmail()!=null) current.setEmail(enterprise.getEmail());
        if (enterprise.getLocalisation()!=null) current.setLocalisation(enterprise.getLocalisation());
        if (enterprise.getTypeIndustry()!=null) current.setTypeIndustry(enterprise.getTypeIndustry());
        if (enterprise.getTypeOrganisation()!=null) current.setTypeOrganisation(enterprise.getTypeOrganisation());
        if (enterprise.getCreationDate()!=null) current.setCreationDate(enterprise.getCreationDate());
        return entrepriseRepository.save(current);
    }

    public List<Enterprise> entrepriseListByUser(Long idUser){
        return entrepriseRepository.getEnterpriseByUsers(idUser);
    }

    public void delete (Enterprise deleteObject){
        deleteObject.setDelete(true);
        entrepriseRepository.save(deleteObject);
    }
}
