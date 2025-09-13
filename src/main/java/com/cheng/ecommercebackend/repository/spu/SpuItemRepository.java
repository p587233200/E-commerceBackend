package com.cheng.ecommercebackend.repository.spu;

import com.cheng.ecommercebackend.entity.product.SPU.SPUItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpuItemRepository extends JpaRepository<SPUItem,String> {

    Page<SPUItem> findByCategory3Id(String categoryId, Pageable pageable);
}
