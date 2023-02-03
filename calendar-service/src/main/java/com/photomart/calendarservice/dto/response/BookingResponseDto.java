package com.photomart.calendarservice.dto.response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponseDto {

    private long bookingId;
    private long userId;
    private long photographerId;
    private long paymentId;
    private long calendarId;
    private Date date;
    private String paymentStatus;
    private String status;

}
