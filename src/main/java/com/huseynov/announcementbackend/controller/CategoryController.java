package com.huseynov.announcementbackend.controller;

import com.huseynov.announcementbackend.dto.CategoryDto;
import com.huseynov.announcementbackend.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/catagories")
public class CategoryController {
    private final CategoryService categoryService = new CategoryService();
    @GetMapping
    public List<CategoryDto> getAllCaregories() {
        return categoryService.getAllCaregories();
    }
}

