package com.huseynov.announcementbackend.dto;

import lombok.Data;

@Data
public class UpdateAnnouncementRequest {
    private String name;
    private String description;
    private Double price;
    private Boolean delivery;
}
