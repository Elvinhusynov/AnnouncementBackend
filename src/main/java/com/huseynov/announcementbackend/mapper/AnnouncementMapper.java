package com.huseynov.announcementbackend.mapper;

import com.huseynov.announcementbackend.dto.AnnouncementDto;
import com.huseynov.announcementbackend.entity.Announcement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
@RequiredArgsConstructor
public class AnnouncementMapper {
    private final CityMapper cityMapper;
    private final CategoryMapper categoryMapper;

    public AnnouncementDto toDto(Announcement announcement) {
        AnnouncementDto announcementDto = new AnnouncementDto();
        announcementDto.setAnnouncementid(announcement.getAnnouncementid());
        announcementDto.setName(announcement.getName());
        announcementDto.setDescription(announcement.getDescription());
        announcementDto.setAnnouncementNumber(announcement.getAnnouncementNumber());
        announcementDto.setPrice(announcement.getPrice());
        announcementDto.setPhoneNumber(announcement.getPhoneNumber());
        announcementDto.setSellerFullName(announcement.getSellerFullName());
        announcementDto.setDelivery(announcement.getDelivery());
        announcementDto.setCreatedDate(announcement.getCreatedDate());
        announcementDto.setModifiedDate(announcement.getModifiedDate());
        announcementDto.setCity(cityMapper.toDto(announcement.getCity()));
        announcementDto.setCategory(categoryMapper.toDto(announcement.getCategory()));

        return announcementDto;
    }
    public List<AnnouncementDto> toDtoList (List<Announcement> announcements) {
    return announcements.stream().map(this::toDto).toList();
    }
}
