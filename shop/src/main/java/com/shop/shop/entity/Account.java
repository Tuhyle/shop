package com.shop.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Builder
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account implements Serializable {
    private static final long serialVersionUID = -2054386655979281969L;

    public static final String ROLE_MANAGER = "ADMIN";
    public static final String ROLE_EMPLOYEE = "USER";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "admin")
    private String admin;

    @Column(name = "registered_at")
    private Date registeredAt;

    @Column(name = "last_login")
    private Date lastLogin;

    @Column(name = "intro")
    private String intro;

    @Column(name = "profile")
    private String profile;
}
