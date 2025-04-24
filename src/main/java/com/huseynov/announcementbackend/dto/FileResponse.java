package com.huseynov.announcementbackend.dto;

import lombok.Data;

@Data
public class FileResponse {

    private String name;
    private String type;
    private byte[] data;
}
