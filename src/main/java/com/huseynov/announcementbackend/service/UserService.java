package com.huseynov.announcementbackend.service;

import com.huseynov.announcementbackend.dao.UserDao;
import com.huseynov.announcementbackend.dto.UserRegisterRequest;
import com.huseynov.announcementbackend.dto.UserResponse;
import com.huseynov.announcementbackend.dto.UserUpdateRequest;
import com.huseynov.announcementbackend.exception.NotFoundException;
import com.huseynov.announcementbackend.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao; // burdada userDaonu inject edirik
    private final UserMapper userMapper;

    public UserResponse create(UserRegisterRequest request) {

        var user = userMapper.toUser(request);
        user = userDao.save(user);

        return userMapper.toResponse(user);

    }

    public UserResponse update(Long id, UserUpdateRequest request) {

        var user = userDao.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        userMapper.populate(request, user);
        user = userDao.save(user);

        return userMapper.toResponse(user);

    }
}
