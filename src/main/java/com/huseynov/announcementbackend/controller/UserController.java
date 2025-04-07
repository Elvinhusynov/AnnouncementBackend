package com.huseynov.announcementbackend.controller;

import com.huseynov.announcementbackend.dto.BaseResponse;
import com.huseynov.announcementbackend.dto.UserResponse;
import com.huseynov.announcementbackend.dto.UserUpdateRequest;
import com.huseynov.announcementbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
        var userResponse = userService.update(request );

        BaseResponse<UserResponse> baseResponse = new BaseResponse<>();
        baseResponse.setData(userResponse);
        return baseResponse;
    }
}
