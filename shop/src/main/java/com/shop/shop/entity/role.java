package com.shop.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum role {
    STATUS_WAIT_CONFIRM(1, "ROLE_MANAGER"),
    STATUS_WAIT_GOOD(2, "ROLE_EMPLOYEE");
    private final int value;
    private final String name;
}