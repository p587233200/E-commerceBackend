package com.cheng.ecommercebackend.entity.product.SKU;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String category3Id;
    private String spuId;
    private String tmId;

    private String skuName;
    private String skuDesc;
    private String price;
    private String weight;
    private String skuDefaultImg;
    private String isSale;

    @OneToMany(mappedBy = "skuItem", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<SkuAttrValue> skuAttrValueList;

    @OneToMany(mappedBy = "skuItem", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<SkuSaleAttrValue> skuSaleAttrValueList;

    // getters and setters
}
