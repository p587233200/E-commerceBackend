package com.cheng.ecommercebackend.service.product;

import com.cheng.ecommercebackend.entity.TradeMarkItem;
import com.cheng.ecommercebackend.repository.tradeMark.TradeMarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TradeMarkService {

    private final TradeMarkRepository tradeMarkRepository;

    // 取得所有
    public List<TradeMarkItem> getAll() {
        return tradeMarkRepository.findAll();
    }
    public Page<TradeMarkItem> getTradeMarksPage(Pageable pageable) {
        return tradeMarkRepository.findAll(pageable);
    }
    // 新增
    public TradeMarkItem save(TradeMarkItem item) {
        item.setId(UUID.randomUUID().toString());
        return tradeMarkRepository.save(item);
    }

    // 修改
    public Optional<TradeMarkItem> update(TradeMarkItem item) {
        return tradeMarkRepository.findById(item.getId()).map(existing -> {
            existing.setTmName(item.getTmName());
            existing.setLogoUrl(item.getLogoUrl());
            return tradeMarkRepository.save(existing);
        });
    }

    // 刪除
    public boolean delete(String id) {
        if (tradeMarkRepository.existsById(id)) {
            tradeMarkRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
