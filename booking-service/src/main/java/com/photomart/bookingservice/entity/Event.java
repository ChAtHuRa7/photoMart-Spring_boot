package com.photomart.bookingservice.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalTime;
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
    private LocalTime time;

    public Event(String locating, LocalTime time) {
        this.locating = locating;
        this.time = time;
    }
}
