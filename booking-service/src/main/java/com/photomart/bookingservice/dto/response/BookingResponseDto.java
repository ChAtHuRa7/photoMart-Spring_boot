package com.photomart.bookingservice.dto.response;

import com.photomart.bookingservice.dto.EventDto;
import com.photomart.bookingservice.dto.PackageDto;
import com.photomart.bookingservice.dto.CustomerDto;
import com.photomart.bookingservice.entity.Customer;
import com.photomart.bookingservice.entity.Event;
import com.photomart.bookingservice.entity.Package;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToOne;
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
    private CustomerDto customer;
    private EventDto event;
    private PackageDto aPackage;

}
