package com.huseynov.announcementbackend.dao;

import com.huseynov.announcementbackend.entity.User;

import java.util.Optional;

public interface UserDao {

    User save (User user); // istifadecini yadda saxlamaq
    Optional<User> findById(Long id);// istifadecini id sine gore axtarmaq

    void delete(Long id);// istifadecini silmek
}
