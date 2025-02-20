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
                String catagoryname = resultSet.getString("catagory_name");
                Category catagory = new Category(catagoryid, catagoryname);
                announcement.setCatagory(catagory);

                announcements.add(announcement);



            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }


        return announcements;
    }
    public void create(Announcement announcement) {
        try(Connection connection = DatabaseConfig.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(QuaryConstants.CREAT_ANNOUNCEMENT_QUARY);
            preparedStatement.setString(1, announcement.getName());
            preparedStatement.setString(2, announcement.getDescription());
            preparedStatement.setLong(3, announcement.getAnnouncementNumber());
            preparedStatement.setDouble(4, announcement.getPrice());
            preparedStatement.setString(5, announcement.getPhoneNumber());
            preparedStatement.setString(6, announcement.getSellerFullName());
            preparedStatement.setBoolean(7, announcement.getDelivery());
            preparedStatement.setLong(8, announcement.getCity().getCityId());
            preparedStatement.setLong(9,announcement.getCatagory().getCatagoryId());

            preparedStatement.execute();




        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }
}
