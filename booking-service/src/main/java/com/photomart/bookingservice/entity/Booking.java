package com.photomart.bookingservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.util.Date;

@Entity
@Table(name = "booking_service")
@Getter
@Setter
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookingId;
    private long userId;
    private long photographerId;
    private long paymentId;
    private long calendarId;
    private Date date;
    private String paymentStatus;
    private String status;
    @OneToOne(cascade = CascadeType.ALL)
    private Customer customer;
    @OneToOne(cascade = CascadeType.ALL)
    private Event event;
    @OneToOne(cascade = CascadeType.ALL)
    private Package aPackage;

    public Booking(long userId, long photographerId, long calendarId, long paymentId,Date date, String paymentStatus,
                   String status) {
        this.userId = userId;
        this.photographerId = photographerId;
        this.calendarId = calendarId;
        this.paymentId = paymentId;
        this.date = date;
        this.paymentStatus = paymentStatus;
        this.status = status;
    }
}
