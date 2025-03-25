package com.huseynov.announcementbackend.controller;

import com.huseynov.announcementbackend.dto.*;
import com.huseynov.announcementbackend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("login")
    public BaseResponse<LoginResponse> login(@RequestBody @Valid UserLoginRequest loginRequest) {
        var loginResponse = userService.login(loginRequest);

        BaseResponse<LoginResponse> baseResponse = new BaseResponse<>();
        baseResponse.setData(loginResponse);
        return baseResponse;
    }

    @PostMapping("sign-up")
    public BaseResponse<UserResponse> register(@RequestBody @Valid UserRegisterRequest request) {

        var userResponse = userService.create(request);

        BaseResponse<UserResponse> baseResponse = new BaseResponse<>();
        baseResponse.setData(userResponse);
        return baseResponse;
    }
}
