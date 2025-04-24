package com.huseynov.announcementbackend.repository;

import com.huseynov.announcementbackend.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}
