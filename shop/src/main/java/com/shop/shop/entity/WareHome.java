package com.shop.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Lê Thị Thúy
 * @created 4/25/2021
 * @project shop
 */
@Entity
@Table(name = "ware_house")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WareHome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name_product")
    private String nameProduct;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "detail")
    private String detail;

    @Column(name = "price")
    private Double price;

    @Column(name = "created_at")
    private Date createAt;

    @Column(name = "updated_at")
    private Date updateAt;
}
