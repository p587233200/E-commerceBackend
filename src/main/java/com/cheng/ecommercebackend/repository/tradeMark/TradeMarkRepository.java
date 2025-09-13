package com.cheng.ecommercebackend.repository.tradeMark;

import com.cheng.ecommercebackend.entity.TradeMarkItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeMarkRepository extends JpaRepository<TradeMarkItem, String> {
}
