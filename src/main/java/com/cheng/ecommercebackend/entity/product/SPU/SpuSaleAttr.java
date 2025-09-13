package com.cheng.ecommercebackend.entity.product.SPU;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
@Data
@Entity
@NoArgsConstructor
public class SpuSaleAttr {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "spuId")
    @JsonBackReference
    @ToString.Exclude
    private SPUItem spuItem;

    private String baseSaleAttrId;
    private String saleAttrName;

    @OneToMany(mappedBy = "spuSaleAttr", cascade = CascadeType.ALL, fetch = FetchType.EAGER,orphanRemoval = true)
    @JsonManagedReference   // 這裡用 ManagedReference
    @ToString.Exclude
    private List<SaleAttrValue> spuSaleAttrValueList;

    public SpuSaleAttr(SPUItem spuItem, String baseSaleAttrId, String saleAttrName) {
        this.spuItem = spuItem;
        this.baseSaleAttrId = baseSaleAttrId;
        this.saleAttrName = saleAttrName;
    }



}
