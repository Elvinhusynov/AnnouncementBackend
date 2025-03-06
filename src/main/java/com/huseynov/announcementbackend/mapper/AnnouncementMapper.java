package com.huseynov.announcementbackend.mapper;

import com.huseynov.announcementbackend.dto.CreateAnnouncementRequest;
import com.huseynov.announcementbackend.dto.AnnouncementResponse;
import com.huseynov.announcementbackend.dto.UpdateAnnouncementRequest;
import com.huseynov.announcementbackend.entity.Announcement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring",
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AnnouncementMapper {
    AnnouncementResponse toResponse(Announcement announcement);

    List<AnnouncementResponse> toResponseList(List<Announcement> announcements);

    @Mapping(target = "announcementNumber", expression = "java(generateAnnouncementNumber())")
    @Mapping(source = "cityId", target = "city.cityId")
    @Mapping(source = "categoryId", target = "category.categoryId")
    @Mapping(target = "createdDate" , expression = "java(getNow())")
    Announcement toEntity(CreateAnnouncementRequest request);

    @Mapping(target = "modifiedDate" , expression = "java(getNow())")
    void populate (UpdateAnnouncementRequest request , @MappingTarget Announcement announcement);

    default Long generateAnnouncementNumber() {
        double d = Math.random() * 100000000;
        return (long) d;

    }
    default LocalDateTime getNow() {
        return LocalDateTime.now();
    }
}
