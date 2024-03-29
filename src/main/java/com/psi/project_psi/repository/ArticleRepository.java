package com.psi.project_psi.repository;

import com.psi.project_psi.models.Article;
import com.psi.project_psi.models.State;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    Iterable<Article> findAllByIsDeleteIsFalse();
    @Query("select article from Article article where article.users.id =:userId and article.isDelete=false")
    List<Article> findByUsers(@Param("userId") Long id);
    @Query("select article from Article article where article.users.id !=:userId and article.isDelete=false")
    List<Article> findByNotUserId(@Param("userId") Long userId);
    @Query("select article from Article article where article.categorie.id =:idCategorie and article.isDelete=false")
    List<Article> findArticleByCategorie(@Param("idCategorie") Long idCategorie);
    @Modifying
    @Transactional
    @Query("update Article article set article.state = ?1 where article.id = ?2")
    int modifyState(State articleState, Long id);
    @Query("select articles from Article articles where articles.categorie.id=:idCategorie and articles.users.id=:idUser and articles.isDelete=false")
    List<Article> findArticleByCategorieAndUsers(@Param("idCategorie") Long idCategorie,@Param("idUser") Long idUser);
    List<Article> findArticleByStateAndIsDeleteIsFalse(State state);
    @Query("select commandes.article.nom, count(*) as nbVentes from Commandes commandes where commandes.isDelete=false and commandes.state=1" +
            " group by commandes.article.id order by nbVentes desc limit 10")
    List<Article> ArticlesPlusVendus();

    @Query("select commandes.article, count(*) as nbVentes from Commandes commandes where commandes.isDelete=false and commandes.article.id =:idCategorie and commandes.state=1" +
            " group by commandes.article.categorie.id order by nbVentes desc limit 10")
    List<Article> ArticlesPlusVendusParCategorie(Long idCategorie);


}
