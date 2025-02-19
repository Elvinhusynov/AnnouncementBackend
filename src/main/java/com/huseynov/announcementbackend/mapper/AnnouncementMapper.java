package com.huseynov.announcementbackend.mapper;

import com.huseynov.announcementbackend.dto.AnnouncementDto;
import com.huseynov.announcementbackend.entity.Announcement;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnnouncementMapper {
    AnnouncementDto toDto(Announcement announcement);
    List<AnnouncementDto> toDtoList(List<Announcement> announcements);
}
