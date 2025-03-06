package com.huseynov.announcementbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")

public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //categoryId arta-arta gedir deməkdi,primary key dir.
    @Column(name = "category_id")
    private Long CategoryId;
    private String Name;
}
