package com.huseynov.announcementbackend.dao;

import com.huseynov.announcementbackend.entity.File;

import java.util.Optional;

public interface FileDao {
    File add(File file);

    void remove(Long id);

    Optional<File> findById(Long id);
}
