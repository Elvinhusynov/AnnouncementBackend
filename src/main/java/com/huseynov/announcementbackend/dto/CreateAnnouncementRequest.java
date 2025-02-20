package com.huseynov.announcementbackend.dto;

import lombok.Data;

@Data
public class CreateAnnouncementRequest {
    private String name;
    private String description;
    private Double price;
    private String phoneNumber;
    private String sellerFullName;
    private Boolean delivery;
    private Long cityId;
    private Long catagoryId;
}
