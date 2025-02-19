package com.huseynov.announcementbackend.dao;

import com.huseynov.announcementbackend.config.DatabaseConfig;
import com.huseynov.announcementbackend.constant.QuaryConstants;
import com.huseynov.announcementbackend.entity.City;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class CityDao {
    public List<City> findAll() {

        List<City> cities = new ArrayList<>();
        try (Connection connection = DatabaseConfig.getConnection()){

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(QuaryConstants.Get_City_List_Query);
            while (resultSet.next()) {
                Long id = resultSet.getLong("city_id");
                String name = resultSet.getString("name");
                City city = new City(id, name);
                cities.add(city);
            }

        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return cities;
    }
}


