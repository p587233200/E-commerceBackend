package com.cheng.ecommercebackend.repository.sku;


import com.cheng.ecommercebackend.entity.product.SKU.SkuItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkuItemRepository extends JpaRepository<SkuItem, String> {
    List<SkuItem> findAllBySpuId(String spuId);
}

