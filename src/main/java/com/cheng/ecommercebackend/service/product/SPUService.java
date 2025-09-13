package com.cheng.ecommercebackend.service.product;


import com.cheng.ecommercebackend.entity.product.SPU.SPUItem;
import com.cheng.ecommercebackend.entity.product.SPU.SaleAttrValue;
import com.cheng.ecommercebackend.entity.product.SPU.SpuImage;
import com.cheng.ecommercebackend.entity.product.SPU.SpuSaleAttr;
import com.cheng.ecommercebackend.repository.spu.SpuItemRepository;
import com.cheng.ecommercebackend.repository.spu.SpuImageRepository;
import com.cheng.ecommercebackend.repository.spu.SpuSaleAttrRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SPUService {
    private final SpuItemRepository spuItemRepository;
    private final SpuSaleAttrRepository spuSaleAttrRepository;
    private final SpuImageRepository spuImageRepository;

    public List<SPUItem> findAll() {
        return spuItemRepository.findAll();
    };

    public List<SpuImage> findImagesBySPUId(String spuId) {
        return spuItemRepository.findById(spuId).get().getSpuImageList();
    };

    public List<SpuSaleAttr> findAttrSaleListBySpuId(String spuId) {
        return spuItemRepository.findById(spuId).get().getSpuSaleAttrList();
    };


    public Page<SPUItem> getSpuItems(int page, int limit, String category3Id) {
        Pageable pageable = PageRequest.of(page - 1, limit);
        Page<SPUItem> spuPage =  spuItemRepository.findByCategory3Id(category3Id, pageable);
        return spuPage;
    }

    public List<SpuSaleAttr> getAllSpuSaleAttrList() {
        return spuSaleAttrRepository.findAll();
    }

    public SPUItem saveNewSpuItem(SPUItem spuItem) {
        spuItem.setId(UUID.randomUUID().toString());
        // 如果有圖片，幫每一張圖片補上 ID & 關聯
        if (spuItem.getSpuImageList() != null) {
            spuItem.getSpuImageList().forEach(image -> {
                image.setId(UUID.randomUUID().toString());
                image.setSpuItem(spuItem); // 建立反向關聯
            });
        }

        // 如果有銷售屬性，幫每一個屬性補上 ID & 關聯
        if (spuItem.getSpuSaleAttrList() != null) {
            spuItem.getSpuSaleAttrList().forEach(attr -> {
                attr.setId(UUID.randomUUID().toString());
                attr.setSpuItem(spuItem); // 建立反向關聯

                // 如果有屬性值，也幫它們設定 ID & 關聯
                if (attr.getSpuSaleAttrValueList() != null) {
                    attr.getSpuSaleAttrValueList().forEach(value -> {
                        value.setId(UUID.randomUUID().toString());
                        value.setSpuSaleAttr(attr); // 建立反向關聯
                    });
                }
            });
        }
        return spuItemRepository.save(spuItem);
    }

    public SPUItem updateSpuItem(SPUItem newSpuItem) {
        SPUItem oldSpuItem = spuItemRepository.findById(newSpuItem.getId()).orElseThrow(() -> new RuntimeException("SPUItem not found"));
        // 2️⃣ 更新基本欄位
        oldSpuItem.setSpuName(newSpuItem.getSpuName());
        oldSpuItem.setDescription(newSpuItem.getDescription());
        oldSpuItem.setTmId(newSpuItem.getTmId());
        oldSpuItem.setCategory3Id(newSpuItem.getCategory3Id());

        // 清空舊的 (需要 orphanRemoval = true)
        oldSpuItem.getSpuImageList().clear();
        oldSpuItem.getSpuSaleAttrList().clear();

        // 更新圖片清單
        if (newSpuItem.getSpuImageList() != null) {
            newSpuItem.getSpuImageList().forEach(image -> {
                image.setSpuItem(oldSpuItem); // 建立反向關聯
                oldSpuItem.getSpuImageList().add(image);
            });
        }

        // 更新銷售屬性清單
        if (newSpuItem.getSpuSaleAttrList() != null) {
            newSpuItem.getSpuSaleAttrList().forEach(SaleAttr -> {
                SaleAttr.setSpuItem(oldSpuItem); // 建立反向關聯
                // 更新屬性值清單
                if (SaleAttr.getSpuSaleAttrValueList() != null) {
                    SaleAttr.getSpuSaleAttrValueList().forEach(SaleAttrValue -> {
                        SaleAttrValue.setSpuSaleAttr(SaleAttr); // 建立反向關聯
                    });
                }
                oldSpuItem.getSpuSaleAttrList().add(SaleAttr);
            });
        }

        return spuItemRepository.save(oldSpuItem);
    }

    public boolean deleteSpuItem (String spuId){
        if(spuItemRepository.existsById(spuId)){
            spuItemRepository.deleteById(spuId);
            return true;
        }
        return false;
    }

}
