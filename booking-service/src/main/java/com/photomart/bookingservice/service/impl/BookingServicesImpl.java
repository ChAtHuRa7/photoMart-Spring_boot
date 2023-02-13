package com.photomart.bookingservice.service.impl;

import com.photomart.bookingservice.dto.requestDTO.*;
import com.photomart.bookingservice.dto.response.BookingResponseDto;
import com.photomart.bookingservice.entity.Booking;
import com.photomart.bookingservice.entity.Customer;
import com.photomart.bookingservice.entity.Event;
import com.photomart.bookingservice.entity.Package;
import com.photomart.bookingservice.exception.NotFoundException;
import com.photomart.bookingservice.repository.BookingRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookingServicesImpl implements com.photomart.bookingservice.service.BookingServices {

    @Autowired
    private BookingRepo bookingRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private WebClient.Builder webClientBuilder;


    @Override
    public BookingResponseDto AddBooking(BookingRequestDto bookingRequestDto) throws Exception{

        Booking booking = new Booking(
                bookingRequestDto.getUserId(),
                bookingRequestDto.getPhotographerId(),
                bookingRequestDto.getCalendarId(),
                0,
                new Date(),
                "pending",
                "pending"

        );

        CustomerRequestDto customerRequestDto = bookingRequestDto.getCustomerRequestDto();

        Customer customer = new Customer(
                customerRequestDto.getFullName(),
                customerRequestDto.getEmail(),
                customerRequestDto.getPhoneNo(),
                customerRequestDto.getAddress(),
                customerRequestDto.getMessage()
        );

        EventRequestDto eventRequestDto = bookingRequestDto.getEventRequestDto();

        Event event = new Event(
                eventRequestDto.getLocating(),
                eventRequestDto.getTime()
        );

        PackageRequestDto packageRequestDto = bookingRequestDto.getPackageRequestDto();

        Package aPackage = new Package(
                packageRequestDto.getPackageTittle(),
                packageRequestDto.getPackageDescription(),
                packageRequestDto.getPrice(),
                packageRequestDto.getPackageStatus()
        );

        booking.setCustomer(customer);
        booking.setEvent(event);
        booking.setAPackage(aPackage);


        Booking newBooking = bookingRepo.save(booking);


        return modelMapper.map(newBooking, BookingResponseDto.class);
    }

    @Override
    public List<BookingResponseDto> getBookingByUserId(long userId) throws NotFoundException{
        List<Booking> bookings = bookingRepo.findValidBookingByUserId(
                userId,
                "timeout",
                "timeout"
        );

        if(!bookings.isEmpty()){
            return bookings.stream().map(booking -> modelMapper
                    .map(validateBooking(booking),BookingResponseDto.class)).toList();
        }
        else{
            throw new NotFoundException("Not found");
        }
    }

    @Override
    public List<BookingResponseDto> getBookingByPhotographerId(long photographerId) {
        List<Booking> bookings = bookingRepo.findValidBookingByPhotographerId(
                photographerId,
                "timeout",
                "timeout");
        if(!bookings.isEmpty()){
            return bookings.stream().map(booking -> modelMapper
                    .map(validateBooking(booking), BookingResponseDto.class)).toList();
        }
        else{
            throw new NotFoundException("Not found");
        }
    }

    @Override
    public BookingResponseDto putCalendarIdById(long bookingId, long calendarId) throws Exception {
        int res = bookingRepo.updateCalendarIdById(calendarId,bookingId);
        if(res == 1){
            return getBookingById(bookingId);
        }
        else {
            throw  new Exception("Not updated");
        }
    }

    private BookingResponseDto getBookingById(long bookingId) {
        Optional<Booking> booking = bookingRepo.findById(bookingId);
        if (booking.isPresent()){
            return  modelMapper.map(validateBooking(booking.get()), BookingResponseDto.class);
        }
        else {
            throw new NotFoundException("Not found");
        }
    }

    @Override
    public BookingResponseDto updatePaymentStatusById(long bookingId, PaymentDetailsRequestDto paymentDetailsRequestDto) throws Exception {
        int res = bookingRepo.updatePaymentIdAndPaymentStatusByBookingId(
                paymentDetailsRequestDto.getPaymentId(),
                paymentDetailsRequestDto.getPaymentStatus(),
                bookingId
        );
        if (res == 1){
            return getBookingById(bookingId);
        }
        else {
            throw  new Exception("Not updated");
        }
    }

    @Override
    public BookingResponseDto updateBookingStatusById(long bookingId, String bookingStatus) throws Exception {
        int res = bookingRepo.updateStatusByBookingId(bookingStatus,bookingId);
        if (res == 1){
            BookingResponseDto booking = getBookingById(bookingId);

            CalenderStatusUpdateRequestDTO calenderStatusUpdateRequestDTO = new CalenderStatusUpdateRequestDTO(
                    booking.getBookingId(),
                    booking.getPhotographerId(),
                    booking.getStatus()
            );

            try {
                webClientBuilder.build()
                        .put()
                        .uri("lb://calendar-service/api/v1/calendars/?id="+booking.getCalendarId())
                        .header("Authorities",",SERVICE")
                        .header("Authorization","token")
                        .accept(MediaType.APPLICATION_JSON)
                        .body(Mono.just(calenderStatusUpdateRequestDTO), CalenderStatusUpdateRequestDTO.class)
                        .retrieve()
                        .bodyToMono(Objects.class)
                        .subscribe(objects -> {});
//                        .block();
            } catch (Exception e) {
//                throw new RuntimeException(e);
//                System.out.println(e);
                System.out.println(e.getMessage());
            }

            return booking;
        }
        else {
            throw new Exception("Not updated");
        }
    }

    @Override
    public BookingResponseDto getBookingByCalendarId(long calendarId) {
        Optional<Booking> booking = bookingRepo.findBookingByCalendarId(calendarId);
        if (booking.isPresent()){
            return  modelMapper.map(validateBooking(booking.get()), BookingResponseDto.class);
        }
        else {
            throw new NotFoundException("Not found");
        }
    }

    private Booking validateBooking(Booking booking){
        if(booking.getDate().before(Date.from(LocalDateTime.now().minusMinutes(30).toInstant(ZoneOffset.of("+05:30"))))
                && booking.getPaymentStatus().equalsIgnoreCase("pending")){
            booking.setPaymentStatus("timeout");
            booking.setStatus("timeout");

            CalenderStatusUpdateRequestDTO calenderStatusUpdateRequestDTO = new CalenderStatusUpdateRequestDTO(
                    booking.getBookingId(),
                    booking.getPhotographerId(),
                    "timeout"
            );
            try {
                updateBookingStatusById(booking.getBookingId(),"timeout");
                updatePaymentStatusById(booking.getBookingId(),new PaymentDetailsRequestDto(0,"timeout"));

                webClientBuilder.build()
                        .put()
                        .uri("lb://calendar-service/api/v1/calendars/?id="+booking.getCalendarId())
                        .header("Authorities",",SERVICE")
                        .header("Authorization","token")
                        .accept(MediaType.APPLICATION_JSON)
                        .body(Mono.just(calenderStatusUpdateRequestDTO), CalenderStatusUpdateRequestDTO.class)
                        .retrieve()
                        .bodyToMono(Objects.class)
                        .subscribe(objects -> {});
//                        .block();
            } catch (Exception e) {
//                throw new RuntimeException(e);
//                System.out.println(e);
                System.out.println(e.getMessage());
            }

        }
        return booking;
    }


}
