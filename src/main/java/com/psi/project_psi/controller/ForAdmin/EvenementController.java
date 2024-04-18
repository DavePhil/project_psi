package com.psi.project_psi.controller.ForAdmin;

import com.psi.project_psi.errors.CustomResponseEntity;
import com.psi.project_psi.models.ForAdmin.Evenement;
import com.psi.project_psi.service.ForAdmin.EvenementService;
import com.psi.project_psi.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
public class EvenementController {
    @Autowired
    private EvenementService service;
    @PostMapping("/evenement")
    public ResponseEntity<?> create(@RequestParam(value = "image", required = false) MultipartFile image,
                                    @RequestParam(value = "datePublication", required = false) String datePublication,
                                    @RequestParam(value = "bigTitle", required = false) String bigTitle,
                                    @RequestParam(value = "sousTitle", required = false)String sousTitle,
                                    @RequestParam(value = "description", required = false)String description) throws IOException {
        if (Utils.verifyImageExtension(image)){
            return CustomResponseEntity.fromKey("TYPE_IMAGE_NON_PRIS_EN_CHARGE", HttpStatus.BAD_REQUEST);
        }
        Evenement object = service.create(image, datePublication, bigTitle, sousTitle, description);
        return ResponseEntity.ok(object);
    }

    @GetMapping("/evenement/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        Optional<Evenement> object = service.getById(id);
        if (object.isEmpty()) return CustomResponseEntity.fromKey("RESSOURCE_INTROUVABLE", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    @GetMapping("/evenements")
    public Iterable<Evenement> getAll(){
        return service.getAll();
    }

    @DeleteMapping("/evenement/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        Optional<Evenement> deleteObject = service.getById(id);
        if (deleteObject.isPresent()) {
            service.delete(deleteObject.get());
            return CustomResponseEntity.fromKey("DELETE_SUCCESSFULLY", HttpStatus.OK);
        }else return CustomResponseEntity.fromKey("RESSOURCE_INTROUVABLE", HttpStatus.BAD_REQUEST);
    }
}
