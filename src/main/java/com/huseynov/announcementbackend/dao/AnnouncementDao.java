package com.huseynov.announcementbackend.dao;

import com.huseynov.announcementbackend.config.DatabaseConfig;
import com.huseynov.announcementbackend.constant.QuaryConstants;
import com.huseynov.announcementbackend.entity.Announcement;
import com.huseynov.announcementbackend.entity.Category;
import com.huseynov.announcementbackend.entity.City;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Repository
public class AnnouncementDao {
    public List<Announcement> findAll() {
        List<Announcement> announcements = new ArrayList<>();
        try (Connection connection = DatabaseConfig.getConnection()) {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QuaryConstants.Get_Announcement_List_Quary);
            while (resultSet.next()) {
                Announcement announcement = new Announcement();
                announcement.setAnnouncementid(resultSet.getLong("announcement_id"));
                announcement.setName(resultSet.getString("name"));
                announcement.setDescription(resultSet.getString("description"));
                announcement.setAnnouncementNumber(resultSet.getLong(("announcement_number")));
                announcement.setPrice(resultSet.getDouble("price"));
                announcement.setPhoneNumber(resultSet.getString("phone_number"));
                announcement.setSellerFullName(resultSet.getString("seller_full_name"));
                announcement.setDelivery(resultSet.getBoolean("delivery"));

                Timestamp createdDate = resultSet.getTimestamp("created_date");
                LocalDateTime createdDateTime = createdDate.toLocalDateTime();
                announcement.setCreatedDate(createdDateTime);

                Timestamp modifiedDate = resultSet.getTimestamp("modified_date");
                LocalDateTime modifiedDateTime = modifiedDate.toLocalDateTime();
                announcement.setModifiedDate(modifiedDateTime);

                Long cityid = resultSet.getLong("city_id");
                String cityname = resultSet.getString("city_name");
                City city = new City(cityid, cityname);
                announcement.setCity(city);

                Long catagoryid = resultSet.getLong("catagory_id");
                String catagoryname = resultSet.getString("category_name");
                Category category = new Category(catagoryid, catagoryname);
                announcement.setCategory(category);

                announcements.add(announcement);



            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }


        return announcements;
    }
}
