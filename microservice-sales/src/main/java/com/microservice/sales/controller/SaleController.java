package com.microservice.sales.controller;

import com.microservice.sales.dto.SaleDTO;
import com.microservice.sales.service.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    ISaleService saleService;

    @GetMapping(value = "/{id_sale}/cart/{id_cart}")
    public ResponseEntity<SaleDTO> getSaleById(@PathVariable Long id_sale, @PathVariable Long id_cart){

        SaleDTO saleDTO = saleService.getSaleDtoById(id_sale, id_cart);

        return ResponseEntity.ok(saleDTO);
    }



}
