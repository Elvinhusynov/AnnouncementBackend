package com.huseynov.announcementbackend.repository;

import com.huseynov.announcementbackend.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
}
