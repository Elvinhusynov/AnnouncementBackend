package com.huseynov.announcementbackend.dao;

import com.huseynov.announcementbackend.config.DatabaseConfig;
import com.huseynov.announcementbackend.constant.QueryConstants;
import com.huseynov.announcementbackend.entity.Category;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class CategoryDao {
    public List<Category> findAll() {
        log.info("Getting categories from database");
        List<Category> categories = new ArrayList<>();
        try (Connection connection = DatabaseConfig.getConnection()) {

            Statement statement = connection.createStatement();

            log.info("Get categories query :{}", QueryConstants.Get_Category_List_Query);

            ResultSet resultSet = statement.executeQuery(QueryConstants.Get_Category_List_Query);
            while (resultSet.next()) {
                Long id = resultSet.getLong("catagory_id");
                String name = resultSet.getString("name");
                Category category = new Category(id, name);
                categories.add(category);

            }

        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return categories;
    }
}
