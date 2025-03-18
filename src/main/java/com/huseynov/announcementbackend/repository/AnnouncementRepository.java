package com.huseynov.announcementbackend.repository;

import com.huseynov.announcementbackend.entity.Announcement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    //Sade queryler ucun asagidaki method.
    Page<Announcement> findAllByNameContainingAndDescriptionContaining(
            String name, String description, Pageable pageable);//elanda ada gore axtaris metodu
    //Containing-meselen elan adi lenovo noutbookdu lenovo yazanda elanin adi tam gelir


//-------------------------------------------------------------------------------------------------

    //jpql - Database qoshulmaq uchun istifade edirik,kompleks query olanda yazilir
    @Query("""
                    select a
                    from Announcement a
                    where a.name like '%' || :name || '%'
                    and a.description like '%' || :description || '%'
            """)
    Page<Announcement> findAllWithJpql(@Param("name") String name, @Param("description") String description,
                                       Pageable pageable);

//--------------------------------------------------------------------------------------------------

    //MySql - dahada murekkeb querylerde istifade olunur
    @Query(value = """
            select *
            from announcements
            where name like concat( '%' , :name , '%')
            and description like concat('%' , :description , '%')
            """, nativeQuery = true)
    Page<Announcement> findAllWithSql (@Param("name")String name, @Param("description")String description,
                                       Pageable pageable);
}
