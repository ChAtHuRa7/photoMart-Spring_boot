package com.photomart.bookingservice.dto.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequestDto {
    private long userId;
    private long photographerId;
    private long calendarId;
    private CustomerRequestDto customerRequestDto;
    private EventRequestDto eventRequestDto;
    private PackageRequestDto packageRequestDto;

}
