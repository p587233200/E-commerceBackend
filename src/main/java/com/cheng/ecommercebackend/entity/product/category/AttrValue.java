package com.cheng.ecommercebackend.entity.product.category;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AttrValue {
    @Id
    private String id;
    private String valueName;
    // 改成 ManyToOne 關聯
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attr_id")
    @JsonBackReference
    @ToString.Exclude  // 避免 toString 遞迴 Lombok @Data + JPA 雙向關聯 → 無限遞迴 (StackOverflowError)
    private AttrInfoList attrInfoList;
}
