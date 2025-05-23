package com.huseynov.announcementbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "announcements")
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long announcementId;
    private String name;
    private String description;
    private Long announcementNumber;
    private Double price;
    private Boolean delivery;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @JoinColumn(name = "user_id",referencedColumnName = "user_id")
    @ManyToOne
    private User user;

    @JoinColumn(name = "city_id" , referencedColumnName = "city_id")
    @ManyToOne
    private City city;

    @JoinColumn(name = "category_id" , referencedColumnName = "category_id")
    @ManyToOne
    private Category category;

    @JoinColumn(name = "file_id" , referencedColumnName = "file_id")
    @OneToOne(cascade = CascadeType.ALL)
    private File file;
}
