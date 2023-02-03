package com.photomart.portfolioservice.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioResponseDto {
    private Long id;
    private Long photographerId;
    private String location;
    private String albumName;
    private List<String> sourceUrls;
}
