package com.shop.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "order")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "token")
    private String token;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "subTotal")
    private Double subTotal;

    @Column(name = "itemDiscount")
    private Double itemDiscount;

    @Column(name = "tax")
    private Double tax;

    @Column(name = "shipping")
    private Double shipping;

    @Column(name = "total")
    private Double total;

    @Column(name = "promo")
    private String promo;

    @Column(name = "discount")
    private Double discount;

    @Column(name = "grandTotal")
    private Double grandTotal;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "middleName")
    private String middleName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "email")
    private String email;

    @Column(name = "line1")
    private String line1;

    @Column(name = "line2")
    private String line2;

    @Column(name = "province")
    private String province;

    @Column(name = "country")
    private String country;

    @Column(name = "content")
    private String content;

    @Column(name = "created_at")
    private Date createAt;

    @Column(name = "updated_at")
    private Date updateAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false, //
            foreignKey = @ForeignKey(name = "TRANSACTION_USER_FK"))
    private Account account;

}
