package com.cheng.ecommercebackend.repository.spu;

import com.cheng.ecommercebackend.entity.product.SPU.SaleAttrValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpuSaleAttrValueRepository extends JpaRepository<SaleAttrValue,String> {
}
