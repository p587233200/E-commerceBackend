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
public class SkuAttrValue {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String attrId;
    private String valueId;
    //這裡來自AttrValue中查找valueId
    private String valueName;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "sku_id")
    private SkuItem skuItem;

    // getters and setters
}
