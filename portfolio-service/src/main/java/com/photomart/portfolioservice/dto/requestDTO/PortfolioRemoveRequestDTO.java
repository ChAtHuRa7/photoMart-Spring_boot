package com.photomart.portfolioservice.dto.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioRemoveRequestDTO {
    long id;
    int photographerId;
}
