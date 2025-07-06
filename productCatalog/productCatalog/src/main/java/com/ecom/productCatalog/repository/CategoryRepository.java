package com.ecom.productCatalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.productCatalog.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
