package com.huseynov.announcementbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "FILES")
@Data
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")

    private Long fileId;
    private String name;
    private String type;
    private LocalDateTime createdAt;
}
