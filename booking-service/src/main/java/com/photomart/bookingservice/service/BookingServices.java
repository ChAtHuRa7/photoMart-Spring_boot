package com.photomart.bookingservice.service;

import com.photomart.bookingservice.dto.requestDTO.BookingRequestDto;
import com.photomart.bookingservice.dto.requestDTO.PaymentDetailsRequestDto;
import com.photomart.bookingservice.dto.response.BookingResponseDto;

import java.util.List;

public interface BookingServices {
    BookingResponseDto AddBooking(BookingRequestDto bookingRequestDto) throws Exception;

    List<BookingResponseDto> getBookingByUserId(long userId);

    List<BookingResponseDto> getBookingByPhotographerId(long photographerId);

    BookingResponseDto putCalendarIdById(long bookingId, long calendarId) throws Exception;

    BookingResponseDto updatePaymentStatusById(long bookingId, PaymentDetailsRequestDto paymentDetailsRequestDto) throws Exception;

    BookingResponseDto updateBookingStatusById(long bookingId, String bookingStatus) throws Exception;

    BookingResponseDto getBookingByCalendarId(long calendarId);
}
