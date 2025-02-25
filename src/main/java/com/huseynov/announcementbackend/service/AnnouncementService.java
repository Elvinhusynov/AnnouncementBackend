package com.huseynov.announcementbackend.service;

import com.huseynov.announcementbackend.dao.AnnouncementDao;
import com.huseynov.announcementbackend.dto.CreateAnnouncementRequest;
import com.huseynov.announcementbackend.dto.AnnouncementResponse;
import com.huseynov.announcementbackend.dto.UpdateAnnouncementRequest;
import com.huseynov.announcementbackend.entity.Announcement;
import com.huseynov.announcementbackend.mapper.AnnouncementMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnnouncementService {
    private final AnnouncementDao announcementDao;
    private final AnnouncementMapper announcementMapper;

    public List<AnnouncementResponse> getAllAnnouncements() {
        List<Announcement> announcements = announcementDao.findAll();

        log.info("Announcements found: {}", announcements);

        return announcementMapper.toResponseList(announcements);
    }

    public void createAnnouncement(CreateAnnouncementRequest request) {
        Announcement announcement = announcementMapper.toEntity(request);

        log.info("Announcement created entity: {}", announcement);

        announcementDao.create(announcement);
    }

    public void updateAnnouncement(Long announcementId, UpdateAnnouncementRequest request) {
        Announcement announcement = announcementMapper.toEntity(announcementId, request);

        log.info("Announcement updated entity: {}", announcement);

        announcementDao.update(announcement);

    }

    public void deleteAnnouncement(Long announcementId) {
        announcementDao.delete(announcementId);
    }

    public AnnouncementResponse getById(Long announcementId) {
        Announcement announcement = announcementDao.getById(announcementId);
        log.info("Announcement found: {}", announcement);
        return announcementMapper.toResponse(announcement);
    }
}
