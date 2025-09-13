package com.cheng.ecommercebackend.repository.sku;

import com.cheng.ecommercebackend.entity.product.category.AttrInfoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttrInfoListRepository extends JpaRepository<AttrInfoList, String> {
    List<AttrInfoList> findByCategoryIdIn(List<String> category1Id);
}
