package com.huseynov.announcementbackend.service;

import com.huseynov.announcementbackend.dao.UserDao;
import com.huseynov.announcementbackend.dto.*;
import com.huseynov.announcementbackend.entity.User;
import com.huseynov.announcementbackend.exception.NotFoundException;
import com.huseynov.announcementbackend.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao; // burdada userDaonu inject edirik
    private final UserMapper userMapper;

    public UserResponse update(UserUpdateRequest request) {
        var username = getUsername();
        var user = getByUsername(username)//->username ilə user-i tapır
                .orElseThrow(() -> new NotFoundException("User not found"));
        userMapper.populate(request, user);
        user = userDao.save(user);

        return userMapper.toResponse(user);
    }

    public Optional<User> getByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public UserResponse getMyInformation() {
        String username = getUsername();
        var user = getByUsername(username)//->username ilə user-i tapır
                .orElseThrow(() -> new NotFoundException("User not found"));
        return userMapper.toResponse(user);
    }

    private static String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();//login olmuş şəxs
    }
}
