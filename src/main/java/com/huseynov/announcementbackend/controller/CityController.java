package com.huseynov.announcementbackend.controller;

import com.huseynov.announcementbackend.dto.CityDto;
import com.huseynov.announcementbackend.service.CityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("api/v1/cities")
@RestController
public class CityController {
    private final CityService cityService = new CityService();

    @GetMapping
    public List<CityDto> getCities() {

        List<CityDto> cityDtoList = cityService.getAll();

        return cityDtoList;
    }

}
