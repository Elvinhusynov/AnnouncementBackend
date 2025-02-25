package com.huseynov.announcementbackend.service;

import com.huseynov.announcementbackend.dao.CityDao;
import com.huseynov.announcementbackend.dto.CityDto;
import com.huseynov.announcementbackend.entity.City;
import com.huseynov.announcementbackend.mapper.CityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class CityService {

    private final CityDao cityDao;
    private final CityMapper cityMapper;


    public List<CityDto> getAll(){
        log.info("Getting all cities");

        List<City> cities = cityDao.findAll();

        log.info("Cities got: {}", cities);

        return cityMapper.toDtoList(cities);

    }
}
