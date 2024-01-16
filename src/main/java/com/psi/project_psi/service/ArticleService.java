package com.psi.project_psi.service;

import com.psi.project_psi.models.Article;
import com.psi.project_psi.models.Categorie;
import com.psi.project_psi.models.State;
import com.psi.project_psi.models.Users;
import com.psi.project_psi.repository.ArticleRepository;
import com.psi.project_psi.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public Article create(Article article){
        return articleRepository.save(article);
    }
    public Iterable<Article> getAll(){
        return articleRepository.findAllByIsDeleteIsFalse();
    }
    public Optional<Article> getById(Long id){
        return articleRepository.findById(id);
    }
    public void delete (Article deleteObject){
        deleteObject.setDelete(true);
        articleRepository.save(deleteObject);
    }
    public Article create(MultipartFile photo, String name, Long prix, Categorie categorie, String description, Users users) throws IOException {
        Article article = new Article();
        String _photo = Utils.addMultiPartFile("photoArticle",photo);
        article.setCategorie(categorie);
        article.setNom(name);
        article.setPrix(prix);
        article.setPhoto(_photo);
        article.setDescription(description);
        article.setUsers(users);
        return articleRepository.save(article);
    }
    public Article updateArticle(MultipartFile photo, String name, Long prix, Categorie categorie, String description, Long id) throws IOException {
        Article _article = articleRepository.findById(id).get();
        if (categorie!=null) _article.setCategorie(categorie);
        if (description!=null) _article.setDescription(description);
        if (!photo.isEmpty()) {
            String _photo = Utils.addMultiPartFile("photoArticle",photo);
            _article.setPhoto(_photo);
        }
        if (name!=null)  _article.setNom(name);
        if (prix!=null) _article.setPrix(prix);
        return articleRepository.save(_article);
    }
    public List<Article> findByUserId(Long id){
       return articleRepository.findByUsers(id);
    }
    public List<Article> findByNotUserId(Long id){
        return articleRepository.findByNotUserId(id);
    }
    public List<Article> findByCategorie(Long idCategorie){
        return articleRepository.findArticleByCategorie(idCategorie);
    }
    public int modifyArticleState(State articleState, Long id){
        return articleRepository.modifyState(articleState, id);
    }
    public List<Article> findByCategorieAndUser(Long idCategorie, Long idUser){
        return articleRepository.findArticleByCategorieAndUsers(idCategorie,idUser);
    }

    public List<Article> findByState(State state){
        return  articleRepository.findArticleByStateAndIsDeleteIsFalse(state);
    }
}
