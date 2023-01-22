package com.photomart.calendarservice.service.impl;

import com.photomart.calendarservice.dto.requestDTO.CalendarEventSaveDTO;
import com.photomart.calendarservice.dto.requestDTO.StatusUpdateDTO;
import com.photomart.calendarservice.dto.response.CalendarResponseDto;
import com.photomart.calendarservice.entity.Calendar;
import com.photomart.calendarservice.repository.CalendarRepo;
import com.photomart.calendarservice.service.CalendarService;
import jakarta.ws.rs.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CalendarServiceImpl implements CalendarService {

    @Autowired
    private CalendarRepo calendarRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CalendarResponseDto getCalendarById(long calendarId) throws NotFoundException{
        Calendar calendar = calendarRepo.findByIdEquals(calendarId);

        if(calendar != null){
            return modelMapper.map(calendar, CalendarResponseDto.class);
        }
        throw new NotFoundException("Not found");
    }

    @Override
    public List<CalendarResponseDto> getCalendarByPhotographerId(long photographerId) throws NotFoundException{
        List<Calendar> calendars = calendarRepo.findAllByPhotographerIdAndStatus(
                photographerId,
                "timeout"
        );
        if (!calendars.isEmpty()){
            return calendars.stream().map(calendar -> modelMapper
                    .map(calendar, CalendarResponseDto.class)).collect(Collectors.toList());
        }
        else {
            throw new NotFoundException("Not Found");
        }
    }
    @Override
    public CalendarResponseDto addCalendarEvent(CalendarEventSaveDTO calendarEventSaveDTO) throws Exception {

        Optional<Calendar> calendar = calendarRepo.findCalendarByIdAndDateAndStatus(
                calendarEventSaveDTO.getPhotographerId(),
                getSimpleDate(calendarEventSaveDTO.getDateTime()),
                "pending",
                "confirmed"
        );

        if(calendar.isEmpty()){

            Calendar newCalendar = new Calendar(
                    calendarEventSaveDTO.getPhotographerId(),
                    calendarEventSaveDTO.getUserId(),
                    calendarEventSaveDTO.getDateTime(),
                    getSimpleDate(calendarEventSaveDTO.getDateTime()),
                    new Date(),
                    calendarEventSaveDTO.getStatus()
            );

            Calendar savedCalendar = calendarRepo.save(newCalendar);

            return modelMapper.map(savedCalendar,CalendarResponseDto.class);
        }

        throw new Exception("Can not Book");


    }

    private String getSimpleDate(Date dateTime) {
        return new SimpleDateFormat("dd/MM/yyyy").format(dateTime);
    }


    @Override
    public List<CalendarResponseDto> getCalendarEventByStatus(long photographerId, String status) throws NotFoundException{
        List<Calendar> calendars = calendarRepo.findByPhotographerIdEqualsAndStatusEquals(photographerId,status);
        if (!calendars.isEmpty()){
            return calendars.stream().map(calendar -> modelMapper
                    .map(calendar, CalendarResponseDto.class)).collect(Collectors.toList());
        }
        else {
            throw new NotFoundException("Not Found");
        }
    }



    @Override
    public List<CalendarResponseDto> updateStatus(Long id, StatusUpdateDTO statusUpdateDTO) throws Exception{
        Calendar calendar = calendarRepo.findByIdEquals(id);
        calendarRepo.updateStatus(statusUpdateDTO.getStatus(),id);
        return getCalendarEventByStatus(statusUpdateDTO.getPhotographerId(),calendar.getStatus());
    }



}
