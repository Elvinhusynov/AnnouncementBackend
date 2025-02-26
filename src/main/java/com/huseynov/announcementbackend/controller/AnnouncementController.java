package com.huseynov.announcementbackend.controller;

import com.huseynov.announcementbackend.dto.BaseResponse;
import com.huseynov.announcementbackend.dto.CreateAnnouncementRequest;
import com.huseynov.announcementbackend.dto.AnnouncementResponse;
import com.huseynov.announcementbackend.dto.UpdateAnnouncementRequest;
import com.huseynov.announcementbackend.service.AnnouncementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/announcements")
@RequiredArgsConstructor
public class AnnouncementController {
    private final AnnouncementService announcementService;

    @GetMapping
    public BaseResponse<List<AnnouncementResponse>>getAnnouncements() {
        log.info("Get announcements API is called");

        List<AnnouncementResponse> responses = announcementService.getAllAnnouncements();

        BaseResponse<List<AnnouncementResponse>> baseResponse = new BaseResponse<>();
        baseResponse.setData(responses);
        return baseResponse;
    }

    @PostMapping
    public void create(@RequestBody @Valid CreateAnnouncementRequest request) {
        log.info("Create announcement API is called , request: {}", request);
        announcementService.createAnnouncement(request);

    }

    @PutMapping("/{announcementId}") //path variable
    public void update(@PathVariable("announcementId") Long announcementId,
                       @RequestBody @Valid UpdateAnnouncementRequest request) {
        log.info("Update announcement API is called, announcementId:{} , request:{}", announcementId, request);
        announcementService.updateAnnouncement(announcementId, request);

    }

    @DeleteMapping("/{announcementId}")
    public void delete(@PathVariable("announcementId") Long announcementId) {
        log.info("Delete announcement API is called, announcementId:{}", announcementId);
        announcementService.deleteAnnouncement(announcementId);
    }

    @GetMapping("/{announcementId}")
    public BaseResponse <AnnouncementResponse> getById(@PathVariable("announcementId") Long announcementId) {
        log.info("Get announcement API is called, announcementId: {}", announcementId);
        AnnouncementResponse responce = announcementService.getById(announcementId);

        BaseResponse<AnnouncementResponse> baseResponse = new BaseResponse<>();
        baseResponse.setData(responce);
        return baseResponse;
    }

}
