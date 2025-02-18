package com.huseynov.announcementbackend.service;

import com.huseynov.announcementbackend.dao.AnnouncementDao;
import com.huseynov.announcementbackend.dto.AnnouncementDto;
import com.huseynov.announcementbackend.entity.Announcement;
import com.huseynov.announcementbackend.mapper.AnnouncementMapper;

import java.util.List;

public class AnnouncementService {
    private final AnnouncementDao announcementDao = new AnnouncementDao();
    private final AnnouncementMapper announcementMapper = new AnnouncementMapper();

    public List<AnnouncementDto> getAllAnnouncements() {
        List<Announcement> announcements = announcementDao.findAll();
        return announcementMapper.toDtoList(announcements);
    }
}
