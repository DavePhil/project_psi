package com.psi.project_psi.service.ForAdmin;

import com.psi.project_psi.models.Article;
import com.psi.project_psi.models.Categorie;
import com.psi.project_psi.models.ForAdmin.Tendances;
import com.psi.project_psi.models.Users;
import com.psi.project_psi.repository.ForAdmin.TendancesRepository;
import com.psi.project_psi.utils.file.FileStorageImpl;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@Service
public class TendancesService {
    @Autowired
    private TendancesRepository repository;
    @Autowired
    FileStorageImpl fileStorage;
    public Iterable<Tendances> getAll(){
        return repository.findAllByIsDeleteIsFalse();
    }
    public Optional<Tendances> getById(Long id){
        return repository.findById(id);
    }
    public void delete (Tendances deleteObject){
        deleteObject.setDelete(true);
        deleteObject.setDeleteAt(new Date());
        repository.save(deleteObject);
    }
    public Tendances create(MultipartFile image1, MultipartFile image2, String editionYear, String characteristics,
                            String editionNews, MultipartFile fileExtractForEdition, MultipartFile filePresseCommunicate) throws IOException {
        Tendances tendances = new Tendances();
        String _image1 = fileStorage.save("photoTendance", image1);
        String _image2 = fileStorage.save("photoTendance", image2);
        String _filePresseCommunicate = fileStorage.save("fileTendance", filePresseCommunicate);
        String _fileExtractForEdition = fileStorage.save("fileTendance", fileExtractForEdition);
        tendances.setCharacteristics(characteristics);
        tendances.setImage1(_image1);
        tendances.setImage2(_image2);
        tendances.setEditionNews(editionNews);
        tendances.setFileExtractForEdition(_fileExtractForEdition);
        tendances.setFilePresseCommunicate(_filePresseCommunicate);
        return repository.save(tendances);
    }

}
