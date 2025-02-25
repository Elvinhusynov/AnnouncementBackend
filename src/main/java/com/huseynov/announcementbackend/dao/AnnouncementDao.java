package com.huseynov.announcementbackend.dao;

import com.huseynov.announcementbackend.config.DatabaseConfig;
import com.huseynov.announcementbackend.constant.QueryConstants;
import com.huseynov.announcementbackend.entity.Announcement;
import com.huseynov.announcementbackend.entity.Category;
import com.huseynov.announcementbackend.entity.City;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository

public class AnnouncementDao {
    public List<Announcement> findAll() {
        List<Announcement> announcements = new ArrayList<>();
        try (Connection connection = DatabaseConfig.getConnection()) {

            Statement statement = connection.createStatement();

            log.info("Get announcement list query: {}", QueryConstants.Get_Announcement_List_Query);

            ResultSet resultSet = statement.executeQuery(QueryConstants.Get_Announcement_List_Query);
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
            log.error(e.getMessage(), e);
        }


        return announcements;
    }

    public void create(Announcement announcement) {
        try (Connection connection = DatabaseConfig.getConnection()) {

            log.info("Create announcement query: {}", QueryConstants.CREAT_ANNOUNCEMENT_QUERY);

            PreparedStatement preparedStatement = connection.prepareStatement(QueryConstants.CREAT_ANNOUNCEMENT_QUERY);
            preparedStatement.setString(1, announcement.getName());
            preparedStatement.setString(2, announcement.getDescription());
            preparedStatement.setLong(3, announcement.getAnnouncementNumber());
            preparedStatement.setDouble(4, announcement.getPrice());
            preparedStatement.setString(5, announcement.getPhoneNumber());
            preparedStatement.setString(6, announcement.getSellerFullName());
            preparedStatement.setBoolean(7, announcement.getDelivery());
            preparedStatement.setLong(8, announcement.getCity().getCityId());
            preparedStatement.setLong(9, announcement.getCatagory().getCategoryId());

            preparedStatement.execute();

        } catch (SQLException e) {
            log.error(e.getMessage(), e);

        }
    }

    public void update(Announcement announcement) {
        try (Connection connection = DatabaseConfig.getConnection()) {

            log.info(" Update announcement query: {}", QueryConstants.UPDATE_ANNOUNCEMENT_QUERY);

            PreparedStatement preparedStatement = connection.prepareStatement(QueryConstants.UPDATE_ANNOUNCEMENT_QUERY);
            preparedStatement.setString(1, announcement.getName());
            preparedStatement.setString(2, announcement.getDescription());
            preparedStatement.setDouble(3, announcement.getPrice());
            preparedStatement.setString(4, announcement.getSellerFullName());
            preparedStatement.setBoolean(5, announcement.getDelivery());
            preparedStatement.setLong(6, announcement.getAnnouncementid());
            preparedStatement.execute();

        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void delete(Long announcementId) {
        try (Connection connection = DatabaseConfig.getConnection()) {

            log.info("Delete announcement query: {}", QueryConstants.DELETE_ANNOUNCEMENT_QUERY);

            PreparedStatement preparedStatement = connection.prepareStatement(QueryConstants.DELETE_ANNOUNCEMENT_QUERY);
            preparedStatement.setLong(1, announcementId);

            preparedStatement.execute();

        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }

    }

    public Announcement getById(Long announcementId) {
        try (Connection connection = DatabaseConfig.getConnection()) {
            log.info("Get announcement by id query: {}", QueryConstants.GET_ANNOUNCEMENT_BY_ID);
            PreparedStatement preparedStatement = connection.prepareStatement(QueryConstants.GET_ANNOUNCEMENT_BY_ID);
            preparedStatement.setLong(1, announcementId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Announcement announcement = new Announcement();
                announcement.setAnnouncementid(resultSet.getLong("announcement_id"));
                announcement.setName(resultSet.getString("name"));
                announcement.setDescription(resultSet.getString("description"));
                announcement.setAnnouncementNumber(resultSet.getLong("announcement_number"));
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

                Long cityId = resultSet.getLong("city_id");
                String cityName = resultSet.getString("city_name");
                City city = new City(cityId, cityName);
                announcement.setCity(city);

                Long categoryId = resultSet.getLong("category_id");
                String categoryName = resultSet.getString("category_name");
                Category category = new Category(categoryId, categoryName);
                announcement.setCatagory(category);

                return announcement;

            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}


