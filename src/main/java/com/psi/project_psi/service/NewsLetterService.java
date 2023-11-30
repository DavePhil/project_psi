package com.psi.project_psi.service;

import com.psi.project_psi.models.NewsLetters;
import com.psi.project_psi.models.Project;
import com.psi.project_psi.repository.NewsLetterRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class NewsLetterService {

    @Autowired
    NewsLetterRepository newsLetterRepository;

    public NewsLetters create(NewsLetters newsLetters){
        return newsLetterRepository.save(newsLetters);
    }

    public Iterable<NewsLetters> getAll(){
        return newsLetterRepository.findAllByIsDeleteIsFalse();
    }

    public Optional<NewsLetters> getById(Long id){
        return newsLetterRepository.findById(id);
    }

    public void deleteById(Long id){
        newsLetterRepository.deleteById(id);
    }

    public void delete (NewsLetters deleteObject){
        deleteObject.setDelete(true);
        newsLetterRepository.save(deleteObject);
    }

}
