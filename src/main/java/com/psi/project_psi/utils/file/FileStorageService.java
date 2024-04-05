package com.psi.project_psi.utils.file;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


public interface FileStorageService {
    void init();
    String save(String locationName, MultipartFile file) throws IOException;
    Resource load(String filename);
    void deleteAll();
    Stream<Path> loadAll();
}
