package com.huseynov.announcementbackend.dao.jdbcimpl;

import com.huseynov.announcementbackend.config.DatabaseConfig;
import com.huseynov.announcementbackend.constant.QueryConstants;
import com.huseynov.announcementbackend.dao.CityDao;
import com.huseynov.announcementbackend.entity.City;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository("cityDaoJdbcImpl")
public class CityDaoJdbcImpl implements CityDao {
    @Override
    public List<City> findAll() {
        log.info("Getting cities from database");

        List<City> cities = new ArrayList<>();
        try (Connection connection = DatabaseConfig.getConnection()) {

            Statement statement = connection.createStatement();
            log.info("Getting cities from database : {}", QueryConstants.Get_City_List_Query);

            ResultSet resultSet = statement.executeQuery(QueryConstants.Get_City_List_Query);
            while (resultSet.next()) {
                Long id = resultSet.getLong("city_id");
                String name = resultSet.getString("name");
                City city = new City(id, name);
                cities.add(city);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return cities;
    }
}

