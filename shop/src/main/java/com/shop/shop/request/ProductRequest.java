package com.shop.shop.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    private String title;

    private String metaTitle;

    private String summary;

    private Integer type;

    private Double price;

    private Double discount;

    private Integer quantity;

    private Integer shop;

    private Date publishedAt;

    private Date startsAt;

    private Date endsAt;

    private String content;

    private MultipartFile photos;
}
