package com.psi.project_psi.controller.spaceMarket;

import com.psi.project_psi.errors.CustomResponseEntity;
import com.psi.project_psi.models.*;
import com.psi.project_psi.service.ArticleService;
import com.psi.project_psi.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    @PostMapping("/article")
    public ResponseEntity<?> create(@RequestParam("description") String description,
                                    @RequestParam("photo") MultipartFile photo,
                                    @RequestParam("user") Users users,
                                    @RequestParam("prix") Long prix,
                                    @RequestParam("nom") String nom,
                                    @RequestParam("categorie") Categorie categorie) throws IOException {
        if (Utils.verifyImageExtension(photo)){
            return CustomResponseEntity.fromKey("TYPE_IMAGE_NON_PRIS_EN_CHARGE", HttpStatus.BAD_REQUEST);
        }
        Article article = articleService.create(photo,nom,prix,categorie,description,users);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }
    @GetMapping("/articles/")
    public Iterable<Article> findAll(){
        return articleService.getAll();
    }
    @GetMapping("/articlesByUser/{idUser}")
    public List<Article> findByUser(@PathVariable("idUser")Long idUser){
        return articleService.findByUserId(idUser);
    }
    @GetMapping("/articleNotByUser/{idUser}")
    public List<Article> findByNotUser(@PathVariable("idUser") Long idUser){
        return articleService.findByNotUserId(idUser);
    }

    @GetMapping("/articleByCategorie/{idCategorie}")
    public List<Article> findByCategorie(@PathVariable("idCategorie") Long idCategorie){
        return articleService.findByCategorie(idCategorie);
    }
    @GetMapping("/article/{id}")
    public ResponseEntity<?> findById(@PathVariable("id")Long id){
        Optional<Article> article = articleService.getById(id);
        if (article.isPresent()) return CustomResponseEntity.fromKey("RESSOURCE_INTROUVABLE", HttpStatus.BAD_REQUEST);
        return  ResponseEntity.ok(article);
    }
    @DeleteMapping("/article/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        Optional<Article> deleteObject = articleService.getById(id);
        if (deleteObject.isPresent()) {
            articleService.delete(deleteObject.get());
            return CustomResponseEntity.fromKey("DELETE_SUCCESSFULLY", HttpStatus.OK);
        }else return CustomResponseEntity.fromKey("RESSOURCE_INTROUVABLE", HttpStatus.BAD_REQUEST);
    }
    @PutMapping("/article/valide/{id}")
    public ResponseEntity<?> valide(@PathVariable("id") Long id){
        Optional<Article> article = articleService.getById(id);
        if (article.isEmpty()) return CustomResponseEntity.fromKey("RESSOURCE_INTROUVABLE", HttpStatus.BAD_REQUEST);
        articleService.modifyArticleState(State.Valide,id);
        return CustomResponseEntity.fromKey("ARTICLE_VALIDE", HttpStatus.OK);
    }
    @PutMapping("/article/rejette/{id}")
    public ResponseEntity<?> rejette(@PathVariable("id") Long id){
        Optional<Article> article = articleService.getById(id);
        if (article.isEmpty()) return CustomResponseEntity.fromKey("RESSOURCE_INTROUVABLE", HttpStatus.BAD_REQUEST);
        articleService.modifyArticleState(State.Rejette,id);
        return CustomResponseEntity.fromKey("ARTICLE_REJETE", HttpStatus.OK);
    }
    @GetMapping("/articleByCategorieAndUser/{idCategorie}/{idUser}")
    public List<Article> findByCategorieAndUser(@PathVariable("idCategorie")Long idCategorie, @PathVariable("idUser")Long idUser){
        return articleService.findByCategorieAndUser(idCategorie, idUser);
    }

    @PutMapping("/article/update/{id}")
    public ResponseEntity<?> update(@RequestParam(name = "description",required = false ) String description,
                                    @RequestParam(value = "photo", required = false) MultipartFile photo,
                                    @RequestParam(value = "prix", required = false) Long prix,
                                    @RequestParam(value = "nom", required = false) String nom,
                                    @RequestParam(value = "categorie", required = false) Categorie categorie,
                                    @PathVariable("id") Long id) throws IOException {
        if (Utils.verifyImageExtension(photo)){
            return CustomResponseEntity.fromKey("TYPE_IMAGE_NON_PRIS_EN_CHARGE", HttpStatus.BAD_REQUEST);
        }
        Article article = articleService.updateArticle(photo,nom,prix,categorie,description,id);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }
    @GetMapping("/articlesValide")
    public List<Article> getArticleValide(){
        return articleService.findByState(State.Valide);
    }
    @GetMapping("/articlesRejette")
    public List<Article> getArticleRej(){
        return articleService.findByState(State.Rejette);
    }
    @GetMapping("/articlesEnAttente")
    public List<Article> getArticleEnAttente(){
        return articleService.findByState(State.EnAttente);
    }
    @GetMapping("/articlesPlusVendus")
    public List<Article> getArticlesPlusVendus(){
        return articleService.articlesPlusVendus();
    }
    @GetMapping("/articlesPlusVendusParCategorie/{idCategorie}")
    public List<Article> getArticlesPlusVendusParCategorie(@PathVariable("idCategorie") Long idCategorie){
        return articleService.articlesPlusVendusParCategorie(idCategorie);
    }
}