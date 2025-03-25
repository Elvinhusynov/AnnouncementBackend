package com.huseynov.announcementbackend.service;

import com.huseynov.announcementbackend.dao.UserDao;
import com.huseynov.announcementbackend.dto.*;
import com.huseynov.announcementbackend.entity.User;
import com.huseynov.announcementbackend.exception.ConflictException;
import com.huseynov.announcementbackend.exception.NotFoundException;
import com.huseynov.announcementbackend.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao; // burdada userDaonu inject edirik
    private final UserMapper userMapper;

    public LoginResponse login(UserLoginRequest loginRequest) {
        log.info("User login request: {}", loginRequest);
        return new LoginResponse(UUID.randomUUID().toString()); //UUID-> random token qaytarir.
    }

    public UserResponse create(UserRegisterRequest request) {
        var user = userMapper.toUser(request);
        checkUsernameExists(user);

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

    private void checkUsernameExists(User user) {
        userDao.findByUsername(user.getUsername())
                .ifPresent(user1 ->
                {
                    throw new ConflictException("Username already exists");
                });
    }

}
