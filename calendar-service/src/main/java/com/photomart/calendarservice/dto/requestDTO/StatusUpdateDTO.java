package com.photomart.calendarservice.dto.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusUpdateDTO {

    private Long id;
    private long photographerId;
    private String status;
}
