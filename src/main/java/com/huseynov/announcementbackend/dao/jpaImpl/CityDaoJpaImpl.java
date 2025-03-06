package com.huseynov.announcementbackend.dao.jpaImpl;

import com.huseynov.announcementbackend.dao.CityDao;
import com.huseynov.announcementbackend.entity.City;
import com.huseynov.announcementbackend.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
@Slf4j
@Repository("cityDaoJpaImpl")
@RequiredArgsConstructor
public class CityDaoJpaImpl implements CityDao {
    private final CityRepository cityRepository;


    @Override
    public List<City> findAll() {
        log.info("Find all cities method is called from Jpa Implementation of CityDao");
        return cityRepository.findAll();
    }
}
