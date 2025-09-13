package com.cheng.ecommercebackend.entity.product.SPU;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SaleAttrValue {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String baseSaleAttrId;
    private String saleAttrValueName;
    private String saleAttrName;
    private String isChecked;
    @ManyToOne
    @JoinColumn(name = "spuSaleAttrId")
    @JsonBackReference
    @ToString.Exclude // 避免 toString 遞迴 Lombok @Data + JPA 雙向關聯 → 無限遞迴 (StackOverflowError)
    private SpuSaleAttr spuSaleAttr;

    public SaleAttrValue(String baseSaleAttrId, String saleAttrValueName, String saleAttrName, String isChecked, SpuSaleAttr spuSaleAttr) {
        this.baseSaleAttrId = baseSaleAttrId;
        this.saleAttrValueName = saleAttrValueName;
        this.saleAttrName = saleAttrName;
        this.isChecked = isChecked;
        this.spuSaleAttr = spuSaleAttr;
    }


}
