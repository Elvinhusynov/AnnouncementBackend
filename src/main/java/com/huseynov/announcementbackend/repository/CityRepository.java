package com.huseynov.announcementbackend.repository;

import com.huseynov.announcementbackend.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
    // <> içərisində entity nin adı,entity icində olan id nin tipi
}
