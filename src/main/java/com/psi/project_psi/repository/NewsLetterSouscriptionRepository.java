package com.psi.project_psi.repository;

import com.psi.project_psi.models.NewsLetterSouscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsLetterSouscriptionRepository extends JpaRepository<NewsLetterSouscription, Long> {
    List<NewsLetterSouscription> findAllByIsDeleteIsFalse();
}
