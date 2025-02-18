package com.huseynov.announcementbackend.service;

import com.huseynov.announcementbackend.dao.AnnouncementDao;
import com.huseynov.announcementbackend.dto.AnnouncementDto;
import com.huseynov.announcementbackend.entity.Announcement;
import com.huseynov.announcementbackend.mapper.AnnouncementMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class AnnouncementService {
    private final AnnouncementDao announcementDao ;
    private final AnnouncementMapper announcementMapper;

    public List<AnnouncementDto> getAllAnnouncements() {
        List<Announcement> announcements = announcementDao.findAll();
        return announcementMapper.toDtoList(announcements);
    }
}
