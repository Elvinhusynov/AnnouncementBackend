package com.huseynov.announcementbackend.mapper;

import com.huseynov.announcementbackend.dto.CategoryDto;
import com.huseynov.announcementbackend.entity.Category;
import com.huseynov.announcementbackend.entity.City;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto toDto(Category category);

    List<CategoryDto> toDtoList(List<Category> categoryList);


}
