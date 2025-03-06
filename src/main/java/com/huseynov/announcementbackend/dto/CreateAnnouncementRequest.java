package com.huseynov.announcementbackend.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestBody;

@Data
public class CreateAnnouncementRequest {

    @NotBlank(message = "Name can not be blank")
    private String name;
    private String description;

    @NotNull(message = "Price can not be null")
    @Min(value = 0, message = "Price must be greater than or equal to zero")
    private Double price;

    @NotNull(message = "phone number can not be null")
    @Size(min = 10 , max = 10 , message = "Phone number must contains 10 characters")
    @Pattern(regexp = "//d{10}" , message = "Phone number must contain only digits")
    private String phoneNumber;

    private String sellerFullName;
    private Boolean delivery;
    @NotNull(message = "City id can not be null")
    private Long cityId;

    @NotNull(message = "Category id can not be null")
    private Long categoryId;
}
