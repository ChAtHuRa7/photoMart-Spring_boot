package com.photomart.bookingservice.repository;

import com.photomart.bookingservice.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface BookingRepo extends JpaRepository<Booking,Long> {
    @Query("select b from Booking b where b.calendarId = ?1")
    Optional<Booking> findBookingByCalendarId(@NonNull long calendarId);
    @Query("""
            select b from Booking b
            where b.photographerId = ?1 and upper(b.paymentStatus) not like upper(?2) and upper(b.status) not like upper(?3)""")
    List<Booking> findValidBookingByPhotographerId(@NonNull long photographerId, @NonNull String paymentStatus, @NonNull String status);
    @Query("""
            select b from Booking b
            where b.userId = ?1 and upper(b.paymentStatus) not like upper(?2) and upper(b.status) not like upper(?3)""")
    List<Booking> findValidBookingByUserId(@NonNull long userId, @NonNull String paymentStatus, @NonNull String status);

    @Transactional
    @Modifying
    @Query("update Booking b set b.status = ?1 where b.bookingId = ?2")
    int updateStatusByBookingId(@NonNull String status, @NonNull long bookingId);
    @Transactional
    @Modifying
    @Query("update Booking b set b.paymentId = ?1, b.paymentStatus = ?2 where b.bookingId = ?3")
    int updatePaymentIdAndPaymentStatusByBookingId(@NonNull long paymentId, @NonNull String paymentStatus, @NonNull long bookingId);
    @Transactional
    @Modifying
    @Query("update Booking b set b.calendarId = ?1 where b.bookingId = ?2")
    int updateCalendarIdById(@NonNull long calendarId, @NonNull long bookingId);
    List<Booking> findByPhotographerId(@NonNull long photographerId);
    List<Booking> findByUserId(@NonNull long userId);
}
