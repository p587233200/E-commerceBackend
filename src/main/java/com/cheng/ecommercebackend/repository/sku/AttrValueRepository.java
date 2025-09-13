package com.cheng.ecommercebackend.repository.sku;

import com.cheng.ecommercebackend.entity.product.category.AttrValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttrValueRepository extends JpaRepository<AttrValue,String> {
}
