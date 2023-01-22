package com.photomart.bookingservice.controller;

import com.photomart.bookingservice.dto.requestDTO.BookingRequestDto;
import com.photomart.bookingservice.dto.requestDTO.PaymentDetailsRequestDto;
import com.photomart.bookingservice.service.BookingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/bookings")
public class BookingController {

    @Autowired
    BookingServices bookingServices;

    @PostMapping(path = "/")
    public ResponseEntity<?> addBook(@RequestBody BookingRequestDto bookingRequestDto){
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(bookingServices.AddBooking(bookingRequestDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED)
                    .body(e);
        }
    }

    @GetMapping(path = "/" , params = "uId")
    public ResponseEntity<?> getBookingByUserId(@RequestParam (value = "uId") long userId){
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(bookingServices.getBookingByUserId(userId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e);
        }
    }

    @GetMapping(path = "/" , params = "pId")
    public ResponseEntity<?> getBookingByPhotographerId(@RequestParam (value = "pId") long photographerId){
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(bookingServices.getBookingByPhotographerId(photographerId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e);
        }
    }
    @PutMapping(path = "/" , params = {"id","cId"})
    public ResponseEntity<?> putCalenderId(@RequestParam("id") long bookingId,@RequestParam("cId") long calendarId){
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(bookingServices.putCalendarIdById(bookingId,calendarId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED)
                    .body(e);
        }
    }
    @PutMapping(path = "/" , params = {"id"})
    public ResponseEntity<?> updatePaymentStatus(@RequestParam("id") long bookingId,
                                                 @RequestBody PaymentDetailsRequestDto paymentDetailsRequestDto){
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(bookingServices.updatePaymentStatusById(bookingId,paymentDetailsRequestDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED)
                    .body(e);
        }
    }
    @PutMapping(path = "/" , params = {"id","bStatus"})
    public ResponseEntity<?> updateBookingStatus(@RequestParam("id") long bookingId,
                                                 @RequestParam("bStatus") String bookingStatus){
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(bookingServices.updateBookingStatusById(bookingId,bookingStatus));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED)
                    .body(e);
        }
    }
}
