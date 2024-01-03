package com.psi.project_psi.repository;

import com.psi.project_psi.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    Iterable<Article> findAllByIsDeleteIsFalse();
    @Query("select article from Article article where article.users.id =: userId")
    List<Article> findByUsers(@Param("idUser") Long id);
    @Query("select article from Article article where article.users.id !=: userId")
    List<Article> findByNotUserId(@Param("userId") Long userId);
    @Query("select article from Article article where article.categorie.id !=: idCategorie")
    List<Article> findArticleByCategorie(@Param("idCategorie") Long idCategorie);

}
