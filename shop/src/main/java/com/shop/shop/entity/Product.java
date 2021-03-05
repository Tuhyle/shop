package com.shop.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity{
    @Column(name = "title")
    private String title;

    @Column(name = "metaTitle")
    private String metaTitle;

    @Column(name = "slug")
    private String slug;

    @Column(name = "summary")
    private String summary;

    @Column(name = "type")
    private Integer type;

    @Column(name = "sku")
    private String sku;

    @Column(name = "price")
    private Double price;

    @Column(name = "discount")
    private Double discount;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "shop")
    private Integer shop;

    @Column(name = "publishedAt")
    private Date publishedAt;

    @Column(name = "startsAt")
    private Date startsAt;

    @Column(name = "endsAt")
    private Date endsAt;

    @Column(name = "content")
    private String content;
}
