package com.huseynov.announcementbackend.mapper;

import com.huseynov.announcementbackend.dto.CategoryDto;
import com.huseynov.announcementbackend.entity.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryMapper {
    public CategoryDto toDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(category.getCatagoryId());
        categoryDto.setName(category.getName());
        return categoryDto;
    }

    public List<CategoryDto> toDtoList(List<Category> categoryList) {
        return categoryList.stream()
                .map(this::toDto)
                .toList();

    }
}
