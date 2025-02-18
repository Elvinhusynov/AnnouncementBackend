package com.huseynov.announcementbackend.dto;

import com.huseynov.announcementbackend.entity.Category;
import com.huseynov.announcementbackend.entity.City;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class AnnouncementDto {
    private Long announcementid;
    private String name;
    private String description;
    private Long announcementNumber;
    private Double price;
    private String phoneNumber;
    private String sellerFullName;
    private Boolean delivery;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private CityDto city;
    private CategoryDto category;
}
