package com.huseynov.announcementbackend.service;

import com.huseynov.announcementbackend.dao.CityDao;
import com.huseynov.announcementbackend.dto.CityDto;
import com.huseynov.announcementbackend.entity.City;
import com.huseynov.announcementbackend.mapper.CityMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CityService {

    private final CityDao cityDao;
    private final  CityMapper cityMapper;

    public List<CityDto> getAll(){
        List<City> cities = cityDao.findAll();

        return cityMapper.toDtoList(cities);

    }
}
