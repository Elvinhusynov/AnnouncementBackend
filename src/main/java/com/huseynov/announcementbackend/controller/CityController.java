package com.huseynov.announcementbackend.controller;

import com.huseynov.announcementbackend.dto.BaseResponse;
import com.huseynov.announcementbackend.dto.CityDto;
import com.huseynov.announcementbackend.service.CityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.List;
@Slf4j
@RequestMapping("api/v1/cities")
@RestController
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @GetMapping
    public BaseResponse<List<CityDto>> getCities() {

    log.info("Get cities API is called");

        List<CityDto> cities = cityService.getAll();

        BaseResponse<List<CityDto>> baseResponse = new BaseResponse<>();
        baseResponse.setData(cities);

        return baseResponse;
    }

}
