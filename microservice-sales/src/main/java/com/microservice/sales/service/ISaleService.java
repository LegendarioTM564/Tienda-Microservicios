package com.microservice.sales.service;

import com.microservice.sales.dto.SaleDTO;
import com.microservice.sales.model.Sale;

public interface ISaleService {

    public Sale getSaleById(Long id_sale);
    public SaleDTO getSaleDtoById(Long id_sale);
    public void saveSale(SaleDTO saleDTO);



}
