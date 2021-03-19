package com.shop.shop.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private Integer id;

    private Double subTotal;

    private Double itemDiscount;

    private Double tax;

    private Double shipping;

    private Double total;

    private String promo;

    private Double discount;

    private Double grandTotal;

    private String firstName;

    private String lastName;

    private String mobile;

    private String email;

    private String line1;

    private String province;

    private String district;

    private String wardCommune;

    private String content;
}
