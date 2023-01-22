package com.photomart.calendarservice.dto;


import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CalendarDTO {

    private Long id;
    private long photographerId;
    private long userId;
    private Date dateTime;
    private String date;
    private Date createdDateTime;
    private int status;


}
