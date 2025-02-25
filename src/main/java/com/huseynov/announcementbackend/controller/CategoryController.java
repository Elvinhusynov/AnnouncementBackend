package com.huseynov.announcementbackend.controller;

import com.huseynov.announcementbackend.dto.CategoryDto;
import com.huseynov.announcementbackend.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("api/v1/catagories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping
    public List<CategoryDto> getAllCategories() {
        log.info("Get all categories API is called");
        return categoryService.getAllCategories();
    }
}

