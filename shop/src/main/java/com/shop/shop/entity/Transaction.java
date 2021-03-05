package com.shop.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction extends BaseEntity{
    @Column(name = "code")
    private String code;

    @Column(name = "type")
    private Integer type;

    @Column(name = "status")
    private boolean status;

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false, //
            foreignKey = @ForeignKey(name = "TRANSACTION_USER_FK"))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId", nullable = false, //
            foreignKey = @ForeignKey(name = "TRANSACTION_ORDER_FK"))
    private Order order;
}
