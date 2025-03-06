package com.huseynov.announcementbackend.dao;

import com.huseynov.announcementbackend.entity.Announcement;

import java.util.List;
import java.util.Optional;

public interface AnnouncementDao {
    List<Announcement> findAll(int page, int size);

    void create(Announcement announcement);

    void update(Announcement announcement);

    void delete(Long announcementId);

    Optional<Announcement> findById(Long announcementId);

    Integer getTotalAnnouncementsCount();
}
