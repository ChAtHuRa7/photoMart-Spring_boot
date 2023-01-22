package com.photomart.portfolioservice.controller;

import com.photomart.portfolioservice.dto.requestDTO.PortfolioSaveRequestDTO;
import com.photomart.portfolioservice.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/portfolios")
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @GetMapping(path = "/",params = "pId")
    public ResponseEntity<?> getPortfolios(@RequestParam("pId") long photographerId){
        try {
            return ResponseEntity.ok(portfolioService.getPortfolioByPhotographerId(photographerId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        }
    }

    @GetMapping(path = "/", params = "id")
    public ResponseEntity<?> getPortfolioById(@RequestParam("id") long portfolioId){
        try {
            return ResponseEntity.ok(portfolioService.getPortfolioById(portfolioId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        }
    }

    @PostMapping(path = "/")
    public ResponseEntity<?> createPortfolio(@RequestBody PortfolioSaveRequestDTO portfolioSaveRequestDTO){
        try {
            return ResponseEntity.ok(portfolioService.addPortfolio(portfolioSaveRequestDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(e);
        }
    }

    @DeleteMapping(path = "/")
    public ResponseEntity<?> removePortfolio(@RequestParam ("id") long portfolioId, @RequestParam("pId") long photographerId){
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(portfolioService.removePortfolio(portfolioId, photographerId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        }
    }

}
