package com.huseynov.announcementbackend.repository;

import com.huseynov.announcementbackend.entity.Announcement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    Page<Announcement> findAllByNameContainingAndDescriptionContaining(
            String name,String description, Pageable pageable);//elanda ada gore axtaris metodu
//Containing-meselen elan adi lenovo noutbookdu lenovo yazanda elanin adi tam gelir
}
