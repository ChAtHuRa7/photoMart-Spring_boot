package com.photomart.calendarservice.service;

import com.photomart.calendarservice.dto.requestDTO.CalendarEventSaveDTO;
import com.photomart.calendarservice.dto.requestDTO.StatusUpdateDTO;
import com.photomart.calendarservice.dto.response.CalendarResponseDto;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

public interface CalendarService {
    List<CalendarResponseDto> getCalendarByPhotographerId(long photographerId) throws NotFoundException;

    CalendarResponseDto addCalendarEvent(CalendarEventSaveDTO calendarEventSaveDTO) throws Exception;

    List<CalendarResponseDto> updateStatus(Long id, StatusUpdateDTO statusUpdateDTO) throws Exception;

    List<CalendarResponseDto> getCalendarEventByStatus(long photographerId, String status) throws NotFoundException;

    CalendarResponseDto getCalendarById(long calendarId);
}
