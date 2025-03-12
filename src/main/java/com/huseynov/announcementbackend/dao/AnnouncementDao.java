package com.huseynov.announcementbackend.dao;

import com.huseynov.announcementbackend.entity.Announcement;
import com.huseynov.announcementbackend.enums.SortDirection;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface AnnouncementDao {
    Page<Announcement> findAll(int page, int size, SortDirection sortCreatedDate,String name,String description);

    void create(Announcement announcement);

    void update(Announcement announcement);

    void delete(Long announcementId);

    Optional<Announcement> findById(Long announcementId);

    Integer getTotalAnnouncementsCount();
}
