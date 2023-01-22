package com.photomart.calendarservice.repository;

import com.photomart.calendarservice.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface CalendarRepo extends JpaRepository <Calendar,Long> {
    @Query("select c from Calendar c where c.photographerId = ?1 and c.status not like ?2")
    List<Calendar> findAllByPhotographerIdAndStatus(@NonNull long photographerId, @NonNull String status);
    @Query("""
            select c from Calendar c
            where c.photographerId = ?1 and upper(c.date) = upper(?2) and (upper(c.status) = upper(?3) or upper(c.status) = upper(?4))""")
    Optional<Calendar> findCalendarByIdAndDateAndStatus(@NonNull long photographerId, @NonNull String date, @NonNull String status, @NonNull String status1);
    @Query("""
            select c from Calendar c
            where c.photographerId = ?1 and upper(c.date) = upper(?2) and upper(c.status) like upper(?3)""")
    Optional<Calendar> findCalenderByIdAndDate(@NonNull long photographerId, @NonNull String date, @NonNull String status);
    Calendar findByIdEquals(Long id);
    List<Calendar> findByPhotographerIdEqualsAndStatusEquals(@NonNull Long photographerId, @NonNull String status);
    @Transactional
    @Modifying
    @Query("update Calendar c set c.status = ?1 where c.id = ?2")
    void updateStatus(@NonNull String status, @NonNull Long id);

    List<Calendar> findAllByPhotographerId(Long photographerId);

}
