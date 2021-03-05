package com.shop.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "product_meta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductMeta extends BaseEntity{
    @Column(name = "key")
    private String key;

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId", nullable = false, //
            foreignKey = @ForeignKey(name = "PRODUCT_PRODUCT_META_FK"))
    private Product product;

}
