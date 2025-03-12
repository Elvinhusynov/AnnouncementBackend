package com.huseynov.announcementbackend.service;

import com.huseynov.announcementbackend.dao.AnnouncementDao;
import com.huseynov.announcementbackend.dto.BaseResponse;
import com.huseynov.announcementbackend.dto.CreateAnnouncementRequest;
import com.huseynov.announcementbackend.dto.AnnouncementResponse;
import com.huseynov.announcementbackend.dto.UpdateAnnouncementRequest;
import com.huseynov.announcementbackend.entity.Announcement;
import com.huseynov.announcementbackend.enums.SortDirection;
import com.huseynov.announcementbackend.exception.NotFoundException;
import com.huseynov.announcementbackend.mapper.AnnouncementMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service

public class AnnouncementService {
    private final AnnouncementDao announcementDao;
    private final AnnouncementMapper announcementMapper;

    public AnnouncementService(
            @Qualifier("announcementDaoJpaImpl") AnnouncementDao announcementDao,
            AnnouncementMapper announcementMapper) {
        this.announcementDao = announcementDao;
        this.announcementMapper = announcementMapper;
    }

    public BaseResponse<List<AnnouncementResponse>> getAllAnnouncements(
            int page, int size, SortDirection sortCreatedDate,String name,String description) {
        Page<Announcement> announcementsPage = announcementDao.findAll(page, size ,sortCreatedDate,name,description);

        List<Announcement> announcements = announcementsPage.getContent();
        log.info("Announcements found: {}", announcements);

        var announcementList = announcementMapper.toResponseList(announcements);

        BaseResponse<List<AnnouncementResponse>> baseResponse = new BaseResponse<>();
        baseResponse.setData(announcementList);
        baseResponse.setPageCount(announcementsPage.getTotalPages());
        return baseResponse;

    }

    public void createAnnouncement(CreateAnnouncementRequest request) {
        Announcement announcement = announcementMapper.toEntity(request);

        log.info("Announcement created entity: {}", announcement);

        announcementDao.create(announcement);
    }

    public void updateAnnouncement(Long announcementId, UpdateAnnouncementRequest request) {

        Optional<Announcement> optAnnouncement = announcementDao.findById(announcementId);
        Announcement announcement = optAnnouncement.orElseThrow(() ->
                new NotFoundException("Announcement is not found with id: " + announcementId));

        announcementMapper.populate(request, announcement);

        log.info("Announcement updated entity: {}", announcement);

        announcementDao.update(announcement);

    }

    public void deleteAnnouncement(Long announcementId) {
        announcementDao.delete(announcementId);
    }

    public AnnouncementResponse getById(Long announcementId) {
        Optional<Announcement> optAnnouncement = announcementDao.findById(announcementId);
        Announcement announcement = optAnnouncement.orElseThrow(() ->
                new NotFoundException("Announcement is not found with id: " + announcementId));

        log.info("Announcement found: {}", announcement);

        return announcementMapper.toResponse(announcement);
    }
}
