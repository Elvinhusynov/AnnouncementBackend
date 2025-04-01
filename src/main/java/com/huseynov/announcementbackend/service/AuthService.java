package com.huseynov.announcementbackend.service;

import com.huseynov.announcementbackend.dao.UserDao;
import com.huseynov.announcementbackend.dto.LoginResponse;
import com.huseynov.announcementbackend.dto.UserLoginRequest;
import com.huseynov.announcementbackend.dto.UserRegisterRequest;
import com.huseynov.announcementbackend.dto.UserResponse;
import com.huseynov.announcementbackend.entity.User;
import com.huseynov.announcementbackend.exception.ConflictException;
import com.huseynov.announcementbackend.exception.NotFoundException;
import com.huseynov.announcementbackend.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService { //login olmaq üçün bu class lazımdır.
    private final UserMapper userMapper;
    private final UserDao userDao;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder; //parolu həşləmək üçündür.

    public LoginResponse login(UserLoginRequest loginRequest) {
        log.info("User login request: {}", loginRequest);

        authenticationManager.authenticate(    //login,parolun düz olub-olmadığını yoxluyur.
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(), loginRequest.getPassword()));

        User user = userDao.findByUsername(loginRequest.getUsername()) //logində xəta olmadısa burda user taplılır
                .orElseThrow(() -> new NotFoundException("User not found"));

        String token = jwtService.generateAccessToken(user); //burda user ə görə accesTokeniGenerasiya edirik.

        return new LoginResponse(token); //ve o tokeni geri qaytarırıq.
    }
    public UserResponse register(UserRegisterRequest request) {
        var user = userMapper.toUser(request);
        checkUsernameExists(user);

        String encodedPassword = passwordEncoder.encode(user.getPassword()); //postmandəki 1234 kodunu həşliyirik.
        user.setPassword(encodedPassword);//həşlənən kodu set eliyirik userin qoydugu passworda

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
