package com.microservice.sales.controller;

import com.microservice.sales.dto.SaleDTO;
import com.microservice.sales.service.ISaleService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    ISaleService saleService;


    @CircuitBreaker(name="microservice-shoppingcart" , fallbackMethod ="fallBackGetSaleById" )
    @Retry(name = "microservice-shoppingcart")
    @GetMapping(value = "/{id_sale}")
    public ResponseEntity<SaleDTO> getSaleById(@PathVariable Long id_sale){

        SaleDTO saleDTO = saleService.getSaleDtoById(id_sale);

        return ResponseEntity.ok(saleDTO);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<String> saveSale(@RequestBody SaleDTO saleDTO){

        saleService.saveSale(saleDTO);

        return ResponseEntity.ok("Venta guardada correctamente");
    }


    public ResponseEntity<SaleDTO> fallBackGetSaleById(Long id_sale, Throwable throwable){
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
    }

}
