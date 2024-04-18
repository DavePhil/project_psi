package com.psi.project_psi.repository.ForAdmin;

import com.psi.project_psi.models.ForAdmin.ProductComing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductComingRepository extends JpaRepository<ProductComing, Long> {
    Iterable<ProductComing> findAllByIsDeleteIsFalse();
}
