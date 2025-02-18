package com.huseynov.announcementbackend.controller;

import com.huseynov.announcementbackend.dto.AnnouncementDto;
import com.huseynov.announcementbackend.service.AnnouncementService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/announcements")
public class AnnouncementController {
    private final AnnouncementService announcementService = new AnnouncementService();
    @GetMapping
    public List<AnnouncementDto> getAllAnnouncements() {
    return announcementService.getAllAnnouncements();
    }
}
