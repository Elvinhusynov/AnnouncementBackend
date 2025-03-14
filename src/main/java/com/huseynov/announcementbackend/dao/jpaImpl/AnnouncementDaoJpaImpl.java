package com.huseynov.announcementbackend.dao.jpaImpl;

import com.huseynov.announcementbackend.dao.AnnouncementDao;
import com.huseynov.announcementbackend.entity.Announcement;
import com.huseynov.announcementbackend.enums.SortDirection;
import com.huseynov.announcementbackend.repository.AnnouncementRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;


import java.util.Optional;


@Slf4j
@Repository("announcementDaoJpaImpl")
@RequiredArgsConstructor
public class AnnouncementDaoJpaImpl implements AnnouncementDao {
    private final AnnouncementRepository announcementRepository;

    @Override
    public Page<Announcement> findAll(int page, int size, SortDirection sortCreatedDate,String name,
                                      String description) {
        log.info("Find all announcements method is called from Jpa Implementation of AnnouncementDao");

        Sort sort = null;
        //sort - database dən sorğu çəkərkən sıralamadan istifadə etmək üçündür.
        if (sortCreatedDate == SortDirection.ASC) {
        sort = Sort.by(Sort.Direction.ASC, "createdDate");
        } else if (sortCreatedDate == SortDirection.DESC) {
            sort = Sort.by(Sort.Direction.DESC, "createdDate");
        }

        Pageable pageable = null;//pagination burda reallasdiririq.
        if(sort != null){
            pageable = PageRequest.of(page-1, size,sort);
        } else {
            pageable = PageRequest.of(page-1, size);
        }

        return announcementRepository.findAllByNameContainingAndDescriptionContaining(name,description,pageable);
    }

    @Override
    public void create(Announcement announcement) {
        log.info("Create announcement is called from Jpa Implementation of AnnouncementDao");

        announcementRepository.save(announcement);// bazaya melumat yazir.
    }

    @Override
    public void update(Announcement announcement) {
        log.info("Update announcement is called from Jpa Implementation of AnnouncementDao");

        announcementRepository.save(announcement);

    }

    @Override
    public void delete(Long announcementId) {
        log.info("Delete announcement is called from Jpa Implementation of AnnouncementDao");

        announcementRepository.deleteById(announcementId);
    }

    @Override
    public Optional<Announcement> findById(Long announcementId) {
        log.info("Find announcement is called from Jpa Implementation of AnnouncementDao");

        return announcementRepository.findById(announcementId);
    }

    @Override
    public Integer getTotalAnnouncementsCount() {
        log.info("Getting total count of announcements is called from Jpa Implementation of AnnouncementDao");

        return (int) announcementRepository.count();
    }
}
