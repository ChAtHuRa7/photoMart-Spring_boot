package com.photomart.calendarservice.dto.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalendarEventSaveDTO {

    private long photographerId;
    private long userId;
    private Date dateTime;
    private String status;


    @Override
    public String toString(){
        return "calender";
    }
}
