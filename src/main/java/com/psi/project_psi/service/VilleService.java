package com.psi.project_psi.service;


import com.psi.project_psi.models.Ville;
import com.psi.project_psi.repository.VilleRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class VilleService {

    @Autowired
    private VilleRepository villeRepository;

    public Ville create(Ville ville){
        return villeRepository.save(ville);
    }

    public Iterable<Ville> getAll(){
        return villeRepository.findAll();
    }

    public Optional<Ville> getById(Long id){
        return villeRepository.findById(id);
    }

    public void deleteById(Long id){
        villeRepository.deleteById(id);
    }

    public List<Ville> getByIdPays(Long idPays) {return  villeRepository.findVilleByPays(idPays);}
}
