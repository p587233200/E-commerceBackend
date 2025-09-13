package com.cheng.ecommercebackend.entity.product.category;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AttrInfoList {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String attrName;
    private String categoryId;
    private String categoryLevel;

    // 一個屬性有多個屬性值
    @OneToMany(mappedBy = "attrInfoList", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    @ToString.Exclude  // 避免 toString 遞迴 Lombok @Data + JPA 雙向關聯 → 無限遞迴 (StackOverflowError)
    private List<AttrValue> attrValueList;

    public AttrInfoList(String attrName, String categoryId, String categoryLevel) {
        this.attrName = attrName;
        this.categoryId = categoryId;
        this.categoryLevel = categoryLevel;
    }
}

