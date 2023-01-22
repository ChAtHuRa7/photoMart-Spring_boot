package com.photomart.portfolioservice.dto.requestDTO;
import com.photomart.portfolioservice.dto.SourceUrlDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioSaveRequestDTO {
    private long photographerId;
    private String location;
    private String albumName;
    private List<SourceUrlDTO> sourceUrls;
}
