package com.cheng.ecommercebackend.repository.spu;

import com.cheng.ecommercebackend.entity.product.SPU.SpuImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpuImageRepository extends JpaRepository<SpuImage, String> {
}
