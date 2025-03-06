package com.huseynov.announcementbackend.service;

import com.huseynov.announcementbackend.dao.CategoryDao;
import com.huseynov.announcementbackend.dto.CategoryDto;
import com.huseynov.announcementbackend.entity.Category;
import com.huseynov.announcementbackend.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service

public class CategoryService {
    private final CategoryDao categoryDao;
    private final CategoryMapper categoryMapper;

    public CategoryService(
            @Qualifier("categoryDaoJpaImpl") CategoryDao categoryDao,
            CategoryMapper categoryMapper) {
        this.categoryDao = categoryDao;
        this.categoryMapper = categoryMapper;
    }

    public List<CategoryDto> getAllCategories() {
         log.info("Getting categories");
         List<Category> categories = categoryDao.findAll();
         log.info("Categories found: {}", categories);
         return categoryMapper.toDtoList(categories);
     }
}
