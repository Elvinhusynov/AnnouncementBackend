package com.huseynov.announcementbackend.dto;

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
    private LocalDate createdDate;
    private LocalDateTime modifiedDate;

}
