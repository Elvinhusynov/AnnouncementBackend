package com.huseynov.announcementbackend.dao.jpaImpl;

import com.huseynov.announcementbackend.dao.CategoryDao;
import com.huseynov.announcementbackend.entity.Category;
import com.huseynov.announcementbackend.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
@Slf4j
@Repository("categoryDaoJpaImpl")
@RequiredArgsConstructor
public class CategoryDaoJpaImpl implements CategoryDao {
    private final CategoryRepository categoryRepository;
    @Override
    public List<Category> findAll() {
        log.info("Find all categories method is called from Jpa Implementation of CategoryDao");
        return categoryRepository.findAll();
    }
}
