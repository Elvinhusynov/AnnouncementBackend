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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service

public class AnnouncementService {
    private final AnnouncementDao announcementDao;
    private final AnnouncementMapper announcementMapper;
    private final UserService userService;

    public AnnouncementService(
            @Qualifier("announcementDaoJpaImpl") AnnouncementDao announcementDao,
            AnnouncementMapper announcementMapper,
            UserService userService) {
        this.announcementDao = announcementDao;
        this.announcementMapper = announcementMapper;
        this.userService = userService;
    }

    public BaseResponse<List<AnnouncementResponse>> getAllAnnouncements(
            int page, int size, SortDirection sortCreatedDate, String name, String description) {
        Page<Announcement> announcementsPage = announcementDao.findAll(page, size, sortCreatedDate, name, description);

        List<Announcement> announcements = announcementsPage.getContent();
        log.info("Announcements found: {}", announcements);

        var announcementList = announcementMapper.toResponseList(announcements);

        BaseResponse<List<AnnouncementResponse>> baseResponse = new BaseResponse<>();
        baseResponse.setData(announcementList);
        baseResponse.setPageCount(announcementsPage.getTotalPages());
        return baseResponse;

    }

    public BaseResponse<List<AnnouncementResponse>> getMyAllAnnouncements(
            int page, int size) {
        String username = getUsername();

        Page<Announcement> announcementsPage = announcementDao.findAllByUsername(page, size, username);
        List<Announcement> announcements = announcementsPage.getContent();

        log.info("Announcements for user {} ,found:{}", username, announcements);

        var announcementList = announcementMapper.toResponseList(announcements);

        BaseResponse<List<AnnouncementResponse>> baseResponse = new BaseResponse<>();
        baseResponse.setData(announcementList);
        baseResponse.setPageCount(announcementsPage.getTotalPages());
        return baseResponse;

    }

    public AnnouncementResponse createAnnouncement(CreateAnnouncementRequest request) {
        Announcement announcement = announcementMapper.toEntity(request);
        log.info("Announcement created entity: {}", announcement);

        String username = getUsername();

        var user = userService.getByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found"));
        announcement.setUser(user);

        announcement = announcementDao.create(announcement);
        return announcementMapper.toResponse(announcement);
    }

    public void updateAnnouncement(Long announcementId, UpdateAnnouncementRequest request) {
        Optional<Announcement> optAnnouncement = announcementDao.findById(announcementId);
        Announcement announcement = optAnnouncement.orElseThrow(() ->
                new NotFoundException("Announcement is not found with id: " + announcementId));
        var username = getUsername();

        if(!announcement.getUser().getUsername().equals(username)) {
            throw new AccessDeniedException("User is not allowed to update announcement");
        }

        announcementMapper.populate(request, announcement);
        log.info("Announcement updated entity: {}", announcement);

        announcementDao.update(announcement);

    }

    public void deleteAnnouncement(Long announcementId) {
        Optional<Announcement> optAnnouncement = announcementDao.findById(announcementId);
        Announcement announcement = optAnnouncement.orElseThrow(() ->
                new NotFoundException("Announcement is not found with id: " + announcementId));
        var username = getUsername();

        if (!announcement.getUser().getUsername().equals(username)) {
            throw new AccessDeniedException("User is not allowed to delete announcement");
        }

        announcementDao.delete(announcementId);

    }

    public AnnouncementResponse getById(Long announcementId) {
        Optional<Announcement> optAnnouncement = announcementDao.findById(announcementId);
        Announcement announcement = optAnnouncement.orElseThrow(() ->
                new NotFoundException("Announcement is not found with id: " + announcementId));

        log.info("Announcement found: {}", announcement);

        return announcementMapper.toResponse(announcement);
    }

    private static String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    }
}
