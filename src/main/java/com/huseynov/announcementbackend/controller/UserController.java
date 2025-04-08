package com.huseynov.announcementbackend.controller;

import com.huseynov.announcementbackend.dto.BaseResponse;
import com.huseynov.announcementbackend.dto.UserResponse;
import com.huseynov.announcementbackend.dto.UserUpdateRequest;
import com.huseynov.announcementbackend.dto.UserUpdateStatusRequest;
import com.huseynov.announcementbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("my-information")
    public BaseResponse<UserResponse> getMyInformation() {
        var userResponse = userService.getMyInformation();

        BaseResponse<UserResponse> baseResponse = new BaseResponse<>();
        baseResponse.setData(userResponse);
        return baseResponse;
    }
    @PutMapping
    public BaseResponse<UserResponse> update(
            @RequestBody UserUpdateRequest request){
        var userResponse = userService.update(request);

        BaseResponse<UserResponse> baseResponse = new BaseResponse<>();
        baseResponse.setData(userResponse);
        return baseResponse;
    }
    @PatchMapping("{userId}/status")
    @PreAuthorize("hasAnyAuthority('ADMIN')")//->ancaq adminə icazə verilir aşağıdakılara
    public BaseResponse<UserResponse> updateUserStatus(
            @PathVariable("userId") Long userId,
            @RequestBody UserUpdateStatusRequest request){
        log.info("Update user status request is called");

        var userResponse = userService.updateUserStatus(userId,request);

        BaseResponse<UserResponse> baseResponse = new BaseResponse<>();
        baseResponse.setData(userResponse);
        return baseResponse;
    }
}
