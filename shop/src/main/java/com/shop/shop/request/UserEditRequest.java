package com.shop.shop.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEditRequest {
    private String firstName;

    private String lastName;

    private String mobile;

    private MultipartFile photos;

    private String address;

    private String photo;

    private String email;
}
