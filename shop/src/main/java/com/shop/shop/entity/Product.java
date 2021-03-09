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

    @Column(name = "meta_title")
    private String metaTitle;

    @Column(name = "summary")
    private String summary;

    @Column(name = "type")
    private Integer type;

    @Column(name = "price")
    private Double price;

    @Column(name = "discount")
    private Double discount;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "shop")
    private Integer shop;

    @Column(name = "published_at")
    private Date publishedAt;

    @Column(name = "starts_at")
    private Date startsAt;

    @Column(name = "end_at")
    private Date endsAt;

    @Column(name = "content")
    private String content;

    @Column(name = "photo")
    private String photo;
}
