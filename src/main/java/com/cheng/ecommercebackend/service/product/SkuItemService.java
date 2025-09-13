package com.cheng.ecommercebackend.service.product;

import com.cheng.ecommercebackend.entity.product.SKU.SkuItem;
import com.cheng.ecommercebackend.repository.sku.SkuItemRepository;
import com.cheng.ecommercebackend.repository.sku.AttrValueRepository;
import com.cheng.ecommercebackend.repository.spu.SpuSaleAttrValueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkuItemService {

    private final SkuItemRepository skuItemRepository;
    private final AttrValueRepository attrValueRepository;
    private final SpuSaleAttrValueRepository spuSaleAttrValueRepository;


    public SkuItem saveNewSkuItem(SkuItem skuItem) {
        if (skuItem.getSkuAttrValueList() != null) {
            skuItem.getSkuAttrValueList().forEach(attr -> {
                attr.setSkuItem(skuItem);
                // 從 AttrValue 表查出 valueName
                attrValueRepository.findById(attr.getValueId()).ifPresent(attrValue -> attr.setValueName(attrValue.getValueName()));
            });
        }
        if (skuItem.getSkuSaleAttrValueList() != null) {
            skuItem.getSkuSaleAttrValueList().forEach(saleAttr -> {
                saleAttr.setSkuItem(skuItem);
                spuSaleAttrValueRepository.findById(saleAttr.getSaleAttrValueId()).ifPresent(spuSaleAttr -> {saleAttr.setSaleAttrValueName(spuSaleAttr.getSaleAttrValueName());});
            });
        }
        return skuItemRepository.save(skuItem);
    }



    public SkuItem updateSkuItem(SkuItem skuItem) {
        // update 跟 save 一樣，差別在 repository.save() 會根據 ID 判斷要 insert 還是 update
        if (skuItem.getSkuAttrValueList() != null) {
            skuItem.getSkuAttrValueList().forEach(attr -> {
                attr.setSkuItem(skuItem);
            });
        }

        if (skuItem.getSkuSaleAttrValueList() != null) {
            skuItem.getSkuSaleAttrValueList().forEach(saleAttr -> {
                saleAttr.setSkuItem(skuItem);
            });
        }
        return skuItemRepository.save(skuItem);
    }

    public List<SkuItem> getSkuItemListBySpuId(String spuId) {
        return skuItemRepository.findAllBySpuId(spuId);
    }

    public Page<SkuItem> getSkuListByPageAndLimit(int page, int limit) {
        Pageable pageable = PageRequest.of(page - 1, limit);
        return skuItemRepository.findAll(pageable);
    }

    public SkuItem getSkuInfo(String skuId) {
        return skuItemRepository.findById(skuId).orElse(null);
    }

    public Boolean deleteSkuItemById(String skuId) {
        if(skuItemRepository.existsById(skuId)) {
            skuItemRepository.deleteById(skuId);
            return true;
        }
        return false;
    }

    public void changeOnSale(String skuId) {
        skuItemRepository.findById(skuId).ifPresent(skuItem -> {
            skuItem.setIsSale("1");
            skuItemRepository.save(skuItem);
        });
    }

    public void changeCancelSaleSale(String skuId) {
        skuItemRepository.findById(skuId).ifPresent(skuItem -> {
            skuItem.setIsSale("0");
            skuItemRepository.save(skuItem);
        });
    }
}

