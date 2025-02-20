package com.huseynov.announcementbackend.controller;

import com.huseynov.announcementbackend.dto.CreateAnnouncementRequest;
import com.huseynov.announcementbackend.dto.AnnouncementResponse;
import com.huseynov.announcementbackend.dto.UpdateAnnouncementRequest;
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
    public void create(@RequestBody CreateAnnouncementRequest request) {
        announcementService.createAnnouncement(request);

    }

    @PutMapping("/{announcementId}") //path variable
    public void update(@PathVariable("announcementId") Long announcementId,
                       @RequestBody UpdateAnnouncementRequest request) {
        announcementService.updateAnnouncement(announcementId,request);

    }
    @DeleteMapping("/{announcementId}")
    public void delete(@PathVariable ("announcementId") Long announcementId) {
        announcementService.deleteAnnouncement(announcementId);
    }

}
