package com.huseynov.announcementbackend.dao;

import com.huseynov.announcementbackend.entity.City;

import java.util.List;

public interface CityDao {
    List<City> findAll();
}
