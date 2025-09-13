package com.cheng.ecommercebackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="trademark")
@Entity
public class TradeMarkItem{
    @Id
    @Column(length = 36)
    private String id;

    @Column(nullable = false)
    private String tmName;

    @Column()
    private String logoUrl;
}
