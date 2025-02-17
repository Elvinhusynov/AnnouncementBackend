package com.huseynov.announcementbackend.dao;

import com.huseynov.announcementbackend.config.DatabaseConfig;
import com.huseynov.announcementbackend.entity.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDao {
    public List<City> findAll() {

        List<City> cities = new ArrayList<>();
        try (Connection connection = DatabaseConfig.getConnection()){

            Statement statement = connection.createStatement();
            String quary = "SELECT * FROM cities";

            ResultSet resultSet = statement.executeQuery(quary);
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


