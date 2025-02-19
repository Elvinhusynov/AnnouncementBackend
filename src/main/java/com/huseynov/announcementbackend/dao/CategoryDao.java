package com.huseynov.announcementbackend.dao;

import com.huseynov.announcementbackend.config.DatabaseConfig;
import com.huseynov.announcementbackend.constant.QuaryConstants;
import com.huseynov.announcementbackend.entity.Category;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
@Repository
public class CategoryDao {
    public List<Category> findAll(){
        List<Category> categories = new ArrayList<>();
        try(Connection connection = DatabaseConfig.getConnection()){

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QuaryConstants.Get_Category_List_Query);
            while(resultSet.next()){
                Long id = resultSet.getLong("catagory_id");
                String name = resultSet.getString("name");
                Category category = new Category(id, name);
                categories.add(category);

            }

        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return categories;
    }
}
