package com.huseynov.announcementbackend.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class AnnouncementRequest {
    private String name;
    private String description;
    private Double price;
    private String phoneNumber;
    private String sellerFullName;
    private Boolean delivery;
    private Long cityId;
    private Long catagoryId;
}
