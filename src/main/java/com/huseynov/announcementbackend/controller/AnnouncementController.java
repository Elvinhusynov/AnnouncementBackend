package com.huseynov.announcementbackend.controller;

import com.huseynov.announcementbackend.dto.BaseResponse;
import com.huseynov.announcementbackend.dto.CreateAnnouncementRequest;
import com.huseynov.announcementbackend.dto.AnnouncementResponse;
import com.huseynov.announcementbackend.dto.UpdateAnnouncementRequest;
import com.huseynov.announcementbackend.enums.SortDirection;
import com.huseynov.announcementbackend.service.AnnouncementService;
import com.huseynov.announcementbackend.service.JwtService;
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
    private final JwtService jwtService;

    @GetMapping
    public BaseResponse<List<AnnouncementResponse>> getAnnouncements(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam(value = "sortByCreatedDate", required = false) SortDirection sortCreatedDate,
            @RequestParam(value = "name", required = false, defaultValue = "") String name,
            @RequestParam(value = "description", required = false, defaultValue = "") String description){
        log.info("Get announcements API is called");

        return announcementService.getAllAnnouncements(page, size, sortCreatedDate, name, description);
    }

    @PostMapping
    public BaseResponse <AnnouncementResponse> create(@RequestBody @Valid CreateAnnouncementRequest request) {
        log.info("Create announcement API is called , request: {}", request);
        var response =  announcementService.createAnnouncement(request);

        BaseResponse<AnnouncementResponse> baseResponse = new BaseResponse<>();
        baseResponse.setData(response);
        return baseResponse;
    }

    @PutMapping("/{announcementId}") //path variable
    public void update(@PathVariable("announcementId") Long announcementId,
                       @RequestBody UpdateAnnouncementRequest request) {
        log.info("Update announcement API is called, announcementId:{} , request:{}", announcementId, request);
        announcementService.updateAnnouncement(announcementId, request);

    }

    @DeleteMapping("/{announcementId}")
    public void delete(@PathVariable("announcementId") Long announcementId) {
        log.info("Delete announcement API is called, announcementId:{}", announcementId);
        announcementService.deleteAnnouncement(announcementId);
    }

    @GetMapping("/{announcementId}")
    public BaseResponse<AnnouncementResponse> getById(@PathVariable("announcementId") Long announcementId) {
        log.info("Get announcement API is called, announcementId: {}", announcementId);
        AnnouncementResponse response = announcementService.getById(announcementId);

        BaseResponse<AnnouncementResponse> baseResponse = new BaseResponse<>();
        baseResponse.setData(response);
        return baseResponse;
    }

}
