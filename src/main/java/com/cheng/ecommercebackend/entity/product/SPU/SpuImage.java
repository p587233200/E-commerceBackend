package com.cheng.ecommercebackend.entity.product.SPU;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpuImage {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String imgName;
    private String imgUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spuId")
    @JsonBackReference
    @ToString.Exclude  // 避免 toString 遞迴 Lombok @Data + JPA 雙向關聯 → 無限遞迴 (StackOverflowError)
    private SPUItem spuItem;

    public SpuImage(String imgName, String imgUrl, SPUItem spuItem) {
        this.imgName = imgName;
        this.imgUrl = imgUrl;
        this.spuItem = spuItem;
    }

}
