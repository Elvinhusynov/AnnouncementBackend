package com.huseynov.announcementbackend.service;

import com.huseynov.announcementbackend.dao.CityDao;
import com.huseynov.announcementbackend.dto.CityDto;
import com.huseynov.announcementbackend.entity.City;
import com.huseynov.announcementbackend.mapper.CityMapper;

import java.util.List;

public class CityService {
    private final CityDao cityDao = new CityDao();
    private final CityMapper cityMapper = new CityMapper();

    public List<CityDto> getAll(){
        List<City> cities = cityDao.findAll();

        return cityMapper.toDtoList(cities);

    }
}
