package com.huseynov.announcementbackend.mapper;

import com.huseynov.announcementbackend.dto.CreateAnnouncementRequest;
import com.huseynov.announcementbackend.dto.AnnouncementResponse;
import com.huseynov.announcementbackend.dto.UpdateAnnouncementRequest;
import com.huseynov.announcementbackend.entity.Announcement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnnouncementMapper {
    AnnouncementResponse toResponse(Announcement announcement);

    List<AnnouncementResponse> toResponseList(List<Announcement> announcements);

    @Mapping(target = "announcementNumber", expression = "java(generateAnnouncementNumber())")
    @Mapping(source = "cityId", target = "city.cityId")
    @Mapping(source = "catagoryId", target = "catagory.catagoryId")
    Announcement toEntity(CreateAnnouncementRequest request);
    Announcement toEntity(Long announcementId,UpdateAnnouncementRequest request);

    default Long generateAnnouncementNumber() {
        double d = Math.random() * 100000000;
        return (long) d;

    }
}
