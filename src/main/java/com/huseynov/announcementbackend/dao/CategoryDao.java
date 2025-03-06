package com.huseynov.announcementbackend.dao;

import com.huseynov.announcementbackend.entity.Category;

import java.util.List;

public interface CategoryDao {
    List<Category> findAll();
}
