package com.huseynov.announcementbackend.controller;

import com.huseynov.announcementbackend.dto.AnnouncementRequest;
import com.huseynov.announcementbackend.dto.AnnouncementResponse;
import com.huseynov.announcementbackend.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/announcements")
@RequiredArgsConstructor
public class AnnouncementController {
    private final AnnouncementService announcementService;

    @GetMapping
    public List<AnnouncementResponse> getAllAnnouncements() {
    return announcementService.getAllAnnouncements();
    }

    @PostMapping
    public void create(@RequestBody AnnouncementRequest request) {
        announcementService.createAnnouncement(request);

    }
}
