package com.photomart.portfolioservice.service;

import com.photomart.portfolioservice.dto.requestDTO.PortfolioSaveRequestDTO;
import com.photomart.portfolioservice.dto.response.PortfolioResponseDto;

import java.util.List;

public interface PortfolioService {
    List<PortfolioResponseDto> getPortfolioByPhotographerId(long photographerId) throws Exception;

    List<PortfolioResponseDto> addPortfolio(PortfolioSaveRequestDTO portfolioSaveRequestDTO) throws Exception;

    List<PortfolioResponseDto> removePortfolio(long portfolioId, long photographerId) throws Exception;

    PortfolioResponseDto getPortfolioById(long portfolioId) throws Exception;
}
