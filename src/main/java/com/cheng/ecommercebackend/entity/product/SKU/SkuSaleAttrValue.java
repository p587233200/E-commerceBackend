package com.cheng.ecommercebackend.entity.product.SKU;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkuSaleAttrValue {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)

    private String id;

    private String saleAttrId;
    private String saleAttrValueId;
    private String saleAttrValueName;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "sku_id")

    private SkuItem skuItem;

}
