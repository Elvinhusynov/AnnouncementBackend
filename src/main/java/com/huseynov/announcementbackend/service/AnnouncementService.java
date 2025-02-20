package com.huseynov.announcementbackend.service;

import com.huseynov.announcementbackend.dao.AnnouncementDao;
import com.huseynov.announcementbackend.dto.CreateAnnouncementRequest;
import com.huseynov.announcementbackend.dto.AnnouncementResponse;
import com.huseynov.announcementbackend.dto.UpdateAnnouncementRequest;
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

    public List<AnnouncementResponse> getAllAnnouncements() {
        List<Announcement> announcements = announcementDao.findAll();
        return announcementMapper.toResponseList(announcements);
    }
    public void createAnnouncement(CreateAnnouncementRequest request) {
    Announcement announcement = announcementMapper.toEntity(request);
    announcementDao.create(announcement);
    }
    public void updateAnnouncement(Long announcementId,UpdateAnnouncementRequest request) {
        Announcement announcement = announcementMapper.toEntity(announcementId,request);
        announcementDao.update(announcement);

    }
    public void deleteAnnouncement(Long announcementId) {
        announcementDao.delete(announcementId);
    }
}
