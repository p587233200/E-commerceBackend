package com.cheng.ecommercebackend.repository.spu;

import com.cheng.ecommercebackend.entity.product.SPU.SpuSaleAttr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpuSaleAttrRepository extends JpaRepository<SpuSaleAttr,String> {
}
