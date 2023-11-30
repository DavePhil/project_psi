package com.psi.project_psi.repository;
import com.psi.project_psi.models.NewsLetters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsLetterRepository extends JpaRepository<NewsLetters, Long> {

    Iterable<NewsLetters> findAllByIsDeleteIsFalse();
}
