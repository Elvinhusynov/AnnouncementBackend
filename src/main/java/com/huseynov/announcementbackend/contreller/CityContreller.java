package com.huseynov.announcementbackend.contreller;

import com.huseynov.announcementbackend.dao.CityDao;
import com.huseynov.announcementbackend.dto.CityDto;
import com.huseynov.announcementbackend.entity.City;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/v1/cities")
@RestController
public class CityContreller {
    private final CityDao cityDao = new CityDao();

    @GetMapping
    public List<CityDto> getCities() {
        List<CityDto> cityDtoList = new ArrayList<>();
        List<City> cities = cityDao.findall();
        for (City city : cities) {
            CityDto cityDto = new CityDto();
            cityDto.setCityId(city.getCityId());
            cityDto.setName(city.getCityname());
            cityDtoList.add(cityDto);
        }
        return cityDtoList;
    }

}
