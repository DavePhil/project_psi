package com.psi.project_psi.controller.ForAdmin;


import com.psi.project_psi.errors.CustomResponseEntity;
import com.psi.project_psi.models.ForAdmin.Tendances;
import com.psi.project_psi.service.ForAdmin.TendancesService;
import com.psi.project_psi.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
public class TendancesController {

    @Autowired
    private TendancesService service;
    @PostMapping("/tendance")
    public ResponseEntity<?> create(@RequestParam(value = "image1", required = false) MultipartFile image1,
                                    @RequestParam(value = "image2", required = false) MultipartFile image2,
                                    @RequestParam(value = "editionYear", required = false) String editionYear,
                                    @RequestParam(value = "characteristics", required = false)String characteristics,
                                    @RequestParam(value = "editionNews", required = false)String editionNews,
                                    @RequestParam(value = "fileExtractForEdition", required = false)MultipartFile fileExtractForEdition,
                                    @RequestParam(value = "filePresseCommunicate", required = false)MultipartFile filePresseCommunicate) throws IOException {
        if (Utils.verifyImageExtension(image1) || Utils.verifyImageExtension(image2)){
            return CustomResponseEntity.fromKey("TYPE_IMAGE_NON_PRIS_EN_CHARGE", HttpStatus.BAD_REQUEST);
        }
        if (Utils.verifyFileExtensionType(fileExtractForEdition) || Utils.verifyFileExtensionType(filePresseCommunicate)){
            return CustomResponseEntity.fromKey("TYPE_CV_NON_PRIS_EN_CHARGE", HttpStatus.BAD_REQUEST);
        }
        Tendances object = service.create(image1, image2, editionYear, characteristics, editionNews, fileExtractForEdition, filePresseCommunicate);
        return ResponseEntity.ok(object);
    }

    @GetMapping("/tendance/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        Optional<Tendances> object = service.getById(id);
        if (object.isEmpty()) return CustomResponseEntity.fromKey("RESSOURCE_INTROUVABLE", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    @GetMapping("/tendances")
    public Iterable<Tendances> getAll(){
        return service.getAll();
    }

    @DeleteMapping("/tendance/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        Optional<Tendances> deleteObject = service.getById(id);
        if (deleteObject.isPresent()) {
            service.delete(deleteObject.get());
            return CustomResponseEntity.fromKey("DELETE_SUCCESSFULLY", HttpStatus.OK);
        }else return CustomResponseEntity.fromKey("RESSOURCE_INTROUVABLE", HttpStatus.BAD_REQUEST);
    }
}
