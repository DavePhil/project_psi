package com.psi.project_psi.service.ForAdmin;

import com.psi.project_psi.models.ForAdmin.IndustryReport;
import com.psi.project_psi.repository.ForAdmin.IndustryReportRepository;
import com.psi.project_psi.utils.file.FileStorageImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@Service
public class IndustryReportService {
    @Autowired
    private IndustryReportRepository repository;
    @Autowired
    FileStorageImpl fileStorage;
    public Iterable<IndustryReport> getAll(){
        return repository.findAllByIsDeleteIsFalse();
    }
    public Optional<IndustryReport> getById(Long id){
        return repository.findById(id);
    }
    public void delete (IndustryReport deleteObject){
        deleteObject.setDelete(true);
        deleteObject.setDeleteAt(new Date());
        repository.save(deleteObject);
    }
    public IndustryReport create(MultipartFile image1, MultipartFile image2, String editionYear, String characteristics,
                                String editionNews, MultipartFile fileExtractForEdition, MultipartFile filePresseCommunicate) throws IOException {
        IndustryReport object = new IndustryReport();
        String _image1 = fileStorage.save("photoIndustryReport", image1);
        String _image2 = fileStorage.save("photoIndustryReport", image2);
        String _filePresseCommunicate = fileStorage.save("fileIndustryReport", filePresseCommunicate);
        String _fileExtractForEdition = fileStorage.save("fileIndustryReport", fileExtractForEdition);
        object.setCharacteristics(characteristics);
        object.setEditionYear(editionYear);
        object.setImage1(_image1);
        object.setImage2(_image2);
        object.setEditionNews(editionNews);
        object.setFileExtractForEdition(_fileExtractForEdition);
        object.setFilePresseCommunicate(_filePresseCommunicate);
        return repository.save(object);
    }

}
