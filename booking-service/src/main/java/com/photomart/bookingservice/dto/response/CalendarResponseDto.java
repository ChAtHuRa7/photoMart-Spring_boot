package com.photomart.bookingservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CalendarResponseDto {

    private Long id;
    private long photographerId;
    private long userId;
    private Date dateTime;
    private String date;
    private Date createdDateTime;
    private String status;

}
