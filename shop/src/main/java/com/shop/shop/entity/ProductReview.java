package com.shop.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "product_review")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductReview extends BaseEntity{
    @Column(name = "title")
    private String title;

    @Column(name = "rating")
    private String rating;

    @Column(name = "published")
    private Integer published;

    @Column(name = "publishedAt")
    private Date publishedAt;

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId", nullable = false, //
            foreignKey = @ForeignKey(name = "PRODUCT_PRODUCT_REVIEW_FK"))
    private Product product;
}
