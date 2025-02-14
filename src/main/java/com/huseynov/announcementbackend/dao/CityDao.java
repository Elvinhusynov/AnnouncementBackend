package com.huseynov.announcementbackend.dao;

import com.huseynov.announcementbackend.entity.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDao {
    public List<City> findall() {
        String url = "jdbc:mysql://localhost:3306/announcement_backend";
        String username = "root";
        String password = "5301";
        List<City> cities = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM cities";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Long id = resultSet.getLong("CityId");
                String name = resultSet.getString("name");
                City city = new City(id, name);
                cities.add(city);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace(System.out);

        }
        return cities;


    }


}


