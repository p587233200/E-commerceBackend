package com.cheng.ecommercebackend.repository.category;

import com.cheng.ecommercebackend.entity.product.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface CategoryRepository extends JpaRepository<Category, String> {
    List<Category> findByCategory1Id(String category1Id);

    List<Category> findByCategory2Id(String category2Id);

    @Query("select c from Category c where c.category1Id is null AND c.category2Id is null")
    List<Category> findByLevelOne();
}
