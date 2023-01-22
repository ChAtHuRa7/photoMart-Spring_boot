package com.photomart.bookingservice.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "event")
@Setter
@Getter
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long eId;
    private String locating;
    private Date date;

    public Event(String locating, Date date) {
        this.locating = locating;
        this.date = date;
    }
}
