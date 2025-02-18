package com.huseynov.announcementbackend.service;

import com.huseynov.announcementbackend.dao.CategoryDao;
import com.huseynov.announcementbackend.dto.CategoryDto;
import com.huseynov.announcementbackend.entity.Category;
import com.huseynov.announcementbackend.mapper.CategoryMapper;

import java.util.List;

public class CategoryService {
    private final CategoryDao categoryDao = new CategoryDao();
    private final CategoryMapper categoryMapper = new CategoryMapper();

     public List<CategoryDto> getAllCaregories() {
         List<Category> categories = categoryDao.findAll();
         return categoryMapper.toDtoList(categories);
     }
}
