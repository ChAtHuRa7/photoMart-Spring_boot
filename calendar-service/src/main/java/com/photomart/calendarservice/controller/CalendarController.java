package com.photomart.calendarservice.controller;


import com.photomart.calendarservice.dto.requestDTO.CalendarEventSaveDTO;
import com.photomart.calendarservice.dto.requestDTO.StatusUpdateDTO;
import com.photomart.calendarservice.entity.Calendar;
import com.photomart.calendarservice.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/calendars")
public class CalendarController {

    @Autowired
    private CalendarService calendarService;


    @GetMapping(path = "/",params = "id")
    public ResponseEntity<?> getCalenderById(@RequestParam("id") long calendarId){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(calendarService.getCalendarById(calendarId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping(path = "/",params = "pId")
    public ResponseEntity<?> getCalenderByPhotographerId(@RequestParam("pId") long photographerId){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(calendarService.getCalendarByPhotographerId(photographerId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping(path = "/",params = {"pId","status"})
    public ResponseEntity<?> getCalenderPending(@RequestParam("pId") long photographerId,
                                                @RequestParam("status") String status){
        try {
            return ResponseEntity.ok(calendarService.getCalendarEventByStatus(photographerId,status));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @PostMapping(path = "/")
    public ResponseEntity<?> addEvent(@RequestBody CalendarEventSaveDTO calendarEventSaveDTO){
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(calendarService.addCalendarEvent(calendarEventSaveDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(e);
        }
    }

    @PutMapping(path = "/" ,params = "id")
    public ResponseEntity<?> updateStatus(@RequestParam("id") long id,@RequestBody StatusUpdateDTO statusUpdateDTO){
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(calendarService.updateStatus(
                    id,
                    statusUpdateDTO
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(e);
        }
    }

}
