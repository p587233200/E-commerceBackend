package com.cheng.ecommercebackend.seeder;

import com.cheng.ecommercebackend.entity.product.category.AttrInfoList;
import com.cheng.ecommercebackend.entity.product.category.AttrValue;
import com.cheng.ecommercebackend.entity.product.category.Category;
import com.cheng.ecommercebackend.repository.sku.AttrInfoListRepository;
import com.cheng.ecommercebackend.repository.sku.AttrValueRepository;
import com.cheng.ecommercebackend.repository.category.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class categorySeeder implements CommandLineRunner {
    private final CategoryRepository categoryRepository;
    private final AttrInfoListRepository attrInfoListRepository;
    private final AttrValueRepository attrValueRepository;
    List<Category> levelOneList = new ArrayList<>();
    List<Category> levelTwoList = new ArrayList<>();
    List<Category> levelThreeList = new ArrayList<>();
    @Transactional
    @Override
    public void run(String... args) throws Exception {
        attrValueRepository.deleteAll();
        attrInfoListRepository.deleteAll();
        categoryRepository.deleteAll();
        seedCategoriesLevelOne();
        seedCategoriesLevelTwo();
        seedCategoriesLevelThree();
        System.out.println("Seeder: Category 資料已生成");

        seedAttrInfoList();
    }
    private void seedCategoriesLevelOne() {
        List<String> item = List.of("通訊", "汽車", "機車");

        // Level 1
        for (int i = 1; i <= 3; i++) {
            Category c1 = new Category();
            c1.setId(String.valueOf(i));
            c1.setName(String.valueOf(item.get(i - 1)));
            levelOneList.add(c1);
        }
        categoryRepository.saveAll(levelOneList);
    }
    private void seedCategoriesLevelTwo() {
         List<String> phoneLevelTwo = List.of("手機通訊", "家用通訊", "無線電通訊");
        // Level 2
        for (int i = 1; i <=3; i++) {
            for (int j = 1; j <= 3; j++) {
                Category c2 = new Category();
                c2.setId(String.valueOf(10*i+j));
                if(i==1){
                    c2.setName(phoneLevelTwo.get(j-1));
                }else {
                    c2.setName(i + "-" + j);
                }
                c2.setCategory1Id(levelOneList.get(i-1).getId());
                levelTwoList.add(c2);
            }
        }
        // Level 3
        categoryRepository.saveAll(levelTwoList);
    }
    private void seedCategoriesLevelThree() {
        List<String> phoneLevelThree = List.of("手機", "手機殼", "手機配件");

        // Level 3
        for (int i = 1; i <= 3; i++) { // 假設有 8 筆
            for(int j = 1; j <=3; j++) {
                for(int k = 1; k <=3; k++) {
                    Category category3 = new Category();
                    category3.setId(String.valueOf(100*i+10*j+k));
                    if(i==1 && j==1){
                        category3.setName(phoneLevelThree.get(k-1));
                    }else {
                        category3.setName(i+"-"+j+"-"+k);
                    }
                    category3.setCategory2Id(levelTwoList.get((i - 1) * 3 + (j - 1)).getId());
                    levelThreeList.add(category3);
                }
            }
        }
        categoryRepository.saveAll(levelThreeList);
    }
    private void seedAttrInfoList() {

        // CPU
        AttrInfoList cpu = new AttrInfoList("cpu型號","1","3");
        attrInfoListRepository.save(cpu);

        attrValueRepository.save(new AttrValue(UUID.randomUUID().toString(), "M1晶片",cpu));
        attrValueRepository.save(new AttrValue(UUID.randomUUID().toString(), "M2晶片",cpu));
        attrValueRepository.save(new AttrValue(UUID.randomUUID().toString(), "M3晶片",cpu));
        attrValueRepository.save(new AttrValue(UUID.randomUUID().toString(), "M4晶片",cpu));
        attrValueRepository.save(new AttrValue(UUID.randomUUID().toString(), "A18 Pro 晶片",cpu));

        // 電池容量
        AttrInfoList battery = new AttrInfoList("電池容量","1","3");
        attrInfoListRepository.save(battery);

        attrValueRepository.save(new AttrValue(UUID.randomUUID().toString(), "1000mAh~2000mAH",battery));
        attrValueRepository.save(new AttrValue(UUID.randomUUID().toString(), "2000mAh~3000mAH",battery));
        attrValueRepository.save(new AttrValue(UUID.randomUUID().toString(), "3000mAh~4000mAH",battery));

        // RAM容量
        AttrInfoList ram = new AttrInfoList("RAM容量","1","3");
        attrInfoListRepository.save(ram);

        attrValueRepository.save(new AttrValue(UUID.randomUUID().toString(), "8GB",ram));
        attrValueRepository.save(new AttrValue(UUID.randomUUID().toString(), "16GB",ram));
        attrValueRepository.save(new AttrValue(UUID.randomUUID().toString(), "32GB",ram));

        // 電腦系統
        AttrInfoList computerAttr = new AttrInfoList("電腦系統","2","3");
        attrInfoListRepository.save(computerAttr);
        attrValueRepository.save(new AttrValue(UUID.randomUUID().toString(), "Windows",computerAttr));
        attrValueRepository.save(new AttrValue(UUID.randomUUID().toString(), "MacOS",computerAttr));
        attrValueRepository.save(new AttrValue(UUID.randomUUID().toString(), "Linux",computerAttr));

        // 家電品牌
        AttrInfoList brandAttr = new AttrInfoList("家電品牌","3","3");
        attrInfoListRepository.save(brandAttr);
        attrValueRepository.save(new AttrValue(UUID.randomUUID().toString(), "Panasonic",brandAttr));
        attrValueRepository.save(new AttrValue(UUID.randomUUID().toString(), "Sony",brandAttr));
        attrValueRepository.save(new AttrValue(UUID.randomUUID().toString(), "Samsung",brandAttr));

        System.out.println("Seeder: AttrInfoList 資料已生成");
    }
}