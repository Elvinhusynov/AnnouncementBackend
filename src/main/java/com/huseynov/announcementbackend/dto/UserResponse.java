package com.huseynov.announcementbackend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huseynov.announcementbackend.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserResponse { // Istifadecini qaytarmaq ucun
    private Long userId;
    private String name;
    private String surname;
    private LocalDate birthdate;
    private String phoneNumber;
    private String email;
    private String username;
    private Role role;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") //HH-saat,mm-dəqiqə,ss-saniyə
    private LocalDate createdDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedDate;

}
