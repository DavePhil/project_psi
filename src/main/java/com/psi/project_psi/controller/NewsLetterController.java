package com.psi.project_psi.controller;

import com.psi.project_psi.models.NewsLetters;
import com.psi.project_psi.service.NewsLetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class NewsLetterController {

    @Autowired
    private NewsLetterService newsLetterService;

    @PostMapping("/newsletter")
    public NewsLetters create(@RequestBody NewsLetters newsLetters){
        return newsLetterService.create(newsLetters);
    }

    @GetMapping("/newsletter/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        Optional<NewsLetters> newsLetters = newsLetterService.getById(id);
        if (!newsLetters.isPresent()) return new ResponseEntity<>("Cette newsLetter n'existe pas", HttpStatus.OK);
        return new ResponseEntity<>(newsLetters, HttpStatus.OK);
    }

    @GetMapping("/newletters")
    public Iterable<NewsLetters> getAll(){
        return newsLetterService.getAll();
    }

    @DeleteMapping("/newsletter/{id}")
    public void delete(@PathVariable("id") Long id){
        newsLetterService.deleteById(id);
    }
}
