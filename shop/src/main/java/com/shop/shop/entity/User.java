package com.shop.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
@Entity
@Builder
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = -2054386655979281969L;

    public static final String ROLE_MANAGER = "ADMIN";
    public static final String ROLE_EMPLOYEE = "USER";
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "email")
    private String email;

    @Column(name = "passwordHash")
    private String passwordHash;

    @Column(name = "admin")
    private String admin;

    @Column(name = "registeredAt")
    private Date registeredAt;

    @Column(name = "lastLogin")
    private Date lastLogin;

    @Column(name = "intro")
    private String intro;

    @Column(name = "profile")
    private String profile;
}
