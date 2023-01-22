package com.photomart.bookingservice.dto.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetailsRequestDto {
    private long paymentId;
    private String paymentStatus;
}
