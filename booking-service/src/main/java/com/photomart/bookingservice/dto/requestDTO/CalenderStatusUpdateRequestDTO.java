package com.photomart.bookingservice.dto.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalenderStatusUpdateRequestDTO {

    private Long id;
    private long photographerId;
    private String status;
}
