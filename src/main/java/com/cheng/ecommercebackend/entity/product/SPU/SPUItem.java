package com.cheng.ecommercebackend.entity.product.SPU;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class SPUItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String spuName;
    private String description;
    private String category3Id;
    private String tmId;

    @OneToMany(mappedBy = "spuItem", cascade = CascadeType.ALL, fetch = FetchType.EAGER,orphanRemoval = true)
    @JsonManagedReference   // 序列化時帶出 SpuSaleAttr
    @ToString.Exclude  // 避免 toString 遞迴 Lombok @Data + JPA 雙向關聯 → 無限遞迴 (StackOverflowError)
    private List<SpuSaleAttr> spuSaleAttrList;

    @OneToMany(mappedBy = "spuItem", cascade = CascadeType.ALL, fetch = FetchType.EAGER,orphanRemoval = true)
    @JsonManagedReference   // 序列化時帶出 SpuSaleAttr
    @ToString.Exclude  // 避免 toString 遞迴 Lombok @Data + JPA 雙向關聯 → 無限遞迴 (StackOverflowError)
    private List<SpuImage> spuImageList;

    public SPUItem(String spuName, String description, String category3Id, String tmId) {
        this.spuName = spuName;
        this.description = description;
        this.category3Id = category3Id;
        this.tmId = tmId;
    }

    public SPUItem(String spuName, String description, String category3Id, String tmId, List<SpuSaleAttr> spuSaleAttrList, List<SpuImage> spuImageList) {
        this.spuName = spuName;
        this.description = description;
        this.category3Id = category3Id;
        this.tmId = tmId;
        this.spuSaleAttrList = spuSaleAttrList;
        this.spuImageList = spuImageList;
    }
}
