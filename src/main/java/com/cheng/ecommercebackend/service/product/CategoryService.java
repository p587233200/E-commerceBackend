package com.cheng.ecommercebackend.service.product;

import com.cheng.ecommercebackend.entity.product.category.AttrInfoList;
import com.cheng.ecommercebackend.entity.product.category.Category;
import com.cheng.ecommercebackend.repository.sku.AttrInfoListRepository;
import com.cheng.ecommercebackend.repository.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final AttrInfoListRepository attrInfoListRepository;

    // 取得所有一級分類
    public List<Category> getCategory1() {
        return categoryRepository.findByLevelOne();
    }

    // 取得指定一級分類下的二級分類
    public List<Category> getCategory2(String category1Id) {
        return categoryRepository.findByCategory1Id(category1Id);
    }

    // 取得指定二級分類下的三級分類
    public List<Category> getCategory3(String category2Id) {
        return categoryRepository.findByCategory2Id(category2Id);
    }

    // 取得屬性列表
    public List<AttrInfoList> getAttrInfoListByCategory(String category1Id, String category2Id, String category3Id) {
        return attrInfoListRepository.findByCategoryIdIn(List.of(category1Id, category2Id, category3Id));
    }

    // 新增或修改屬性
    public AttrInfoList saveAttrInfo(AttrInfoList attrInfoList) {
        // 如果沒有 id，就新建
        if (attrInfoList.getId() == null || attrInfoList.getId().isEmpty()) {
            attrInfoList.setId(java.util.UUID.randomUUID().toString());
        }

        // 確保每個 AttrValue 都有 id 並設定關聯
        if (attrInfoList.getAttrValueList() != null) {
            attrInfoList.getAttrValueList().forEach(value -> {
                if (value.getId() == null || value.getId().isEmpty()) {
                    value.setId(java.util.UUID.randomUUID().toString());
                }
                value.setAttrInfoList(attrInfoList); // 設定關聯，取代手動設 attrId
            });
        }

        return attrInfoListRepository.save(attrInfoList);
    }

    // 刪除屬性
    public boolean deleteAttr(String attrId) {
        if (attrInfoListRepository.existsById(attrId)) {
            attrInfoListRepository.deleteById(attrId);
            return true;
        }
        return false;
    }

}
