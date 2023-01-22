package com.photomart.calendarservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "calender_service")
@Getter
@Setter
@NoArgsConstructor
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long photographerId;
    private long userId;
    private Date dateTime;
    private String date;
    private Date createdDateTime;
    private String status;

    public Calendar(long photographerId, long userId, Date dateTime, String date,Date createdDateTime, String status) {
        this.photographerId = photographerId;
        this.userId = userId;
        this.dateTime = dateTime;
        this.date = date;
        this.createdDateTime = createdDateTime;
        this.status = status;
    }
}
