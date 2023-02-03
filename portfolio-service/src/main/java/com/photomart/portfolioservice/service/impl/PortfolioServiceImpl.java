package com.photomart.portfolioservice.service.impl;

import com.photomart.portfolioservice.dto.SourceUrlDTO;
import com.photomart.portfolioservice.dto.response.PortfolioResponseDto;
import com.photomart.portfolioservice.dto.response.SourceUrlResponseDto;
import com.photomart.portfolioservice.entity.Portfolio;
import com.photomart.portfolioservice.entity.SourceUrl;
import com.photomart.portfolioservice.repository.PortfolioRepo;
import com.photomart.portfolioservice.dto.requestDTO.PortfolioSaveRequestDTO;
import com.photomart.portfolioservice.service.PortfolioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PortfolioServiceImpl implements PortfolioService {

    @Autowired
    private PortfolioRepo portfolioRepo;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public PortfolioResponseDto getPortfolioById(long portfolioId) throws Exception {
        Optional<Portfolio> portfolio = portfolioRepo.findById(portfolioId);
        if(portfolio.isPresent()){
            return modelMapper.map(portfolio.get(),PortfolioResponseDto.class);
        }
        throw new Exception("portfolio Not found");
    }

    @Override
    public List<PortfolioResponseDto> getPortfolioByPhotographerId(long photographerId) throws Exception{
        List<Portfolio> portfolios = portfolioRepo.findByPhotographerId(photographerId);
        if(portfolios.isEmpty()){
            throw new Exception("portfolio Not found");
        }

        return portfolios.stream()
                .map( portfolio -> new PortfolioResponseDto(
                        portfolio.getId(),
                        portfolio.getPhotographerId(),
                        portfolio.getLocation(),
                        portfolio.getAlbumName(),
                        portfolio.getSourceUrls().stream().map(SourceUrl::getUrl).toList()
                )).toList();
    }

    @Override
    public List<PortfolioResponseDto> addPortfolio(PortfolioSaveRequestDTO portfolioSaveRequestDTO) throws Exception{
        long count = portfolioRepo.countByPhotographerId(portfolioSaveRequestDTO.getPhotographerId());
        if(count<3){
            Portfolio portfolio = new Portfolio(
                    portfolioSaveRequestDTO.getPhotographerId(),
                    portfolioSaveRequestDTO.getLocation(),
                    portfolioSaveRequestDTO.getAlbumName()
            );

            List<SourceUrl> sourceUrls = portfolioSaveRequestDTO.getSourceUrls()
                    .stream()
                    .map(this::mapToDTO).toList();

            portfolio.setSourceUrls(sourceUrls);

            portfolioRepo.save(portfolio);
        }
        else{
            throw new Exception("Can not add more than 3");
        }

        return getPortfolioByPhotographerId(portfolioSaveRequestDTO.getPhotographerId());
    }

    private SourceUrl mapToDTO(SourceUrlDTO sourceUrlDTO) {
        return new SourceUrl(
                sourceUrlDTO.getUrl()
        );
    }

    @Override
    public List<PortfolioResponseDto> removePortfolio(long portfolioId, long photographerId) throws Exception{
        if(portfolioRepo.existsById(portfolioId)){
            portfolioRepo.deleteById(portfolioId);
        }
        else {
            throw new Exception("portfolio Not found");
        }
        return getPortfolioByPhotographerId(photographerId);
    }



}
