package com.huseynov.announcementbackend.mapper;

import com.huseynov.announcementbackend.dto.CityDto;
import com.huseynov.announcementbackend.entity.City;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CityMapper {
    List<CityDto> toDtoList(List<City> cities);

    CityDto toDto(City city);
}
