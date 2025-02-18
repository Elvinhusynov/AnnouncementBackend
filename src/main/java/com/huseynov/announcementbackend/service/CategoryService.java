package com.huseynov.announcementbackend.service;

import com.huseynov.announcementbackend.dao.CategoryDao;
import com.huseynov.announcementbackend.dto.CategoryDto;
import com.huseynov.announcementbackend.entity.Category;
import com.huseynov.announcementbackend.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryDao categoryDao ;
    private final CategoryMapper categoryMapper;

     public List<CategoryDto> getAllCaregories() {
         List<Category> categories = categoryDao.findAll();
         return categoryMapper.toDtoList(categories);
     }
}
