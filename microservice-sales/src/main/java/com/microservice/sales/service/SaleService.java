package com.microservice.sales.service;

import com.microservice.sales.client.ShoppingCartClient;
import com.microservice.sales.dto.CartDTO;
import com.microservice.sales.dto.SaleDTO;
import com.microservice.sales.model.Sale;
import com.microservice.sales.repository.ISaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SaleService implements ISaleService{

    @Autowired
    ISaleRepository saleRepository;

    @Autowired
    ShoppingCartClient shoppingCartClient;

    @Override
    public Sale getSaleById(Long id_sale) {

        Sale sale = saleRepository.findById(id_sale).orElse(null);

        return sale;
    }

    @Override
    public SaleDTO getSaleDtoById(Long id_sale) {
        //Se busca la venta en la base de datos.
        Sale sale = this.getSaleById(id_sale);
        SaleDTO saleDTO = new SaleDTO();

        //Consumimos el cliente del carrito para consultar los datos del carrito.
        CartDTO cartDTO = shoppingCartClient.getCartById(sale.getId_cart());

        //Le asigno los datos a SaleDTO
        saleDTO.setId_sale(sale.getId_sale());
        saleDTO.setCreate_at(sale.getCreate_at());
        saleDTO.setId_cart(sale.getId_cart());
        saleDTO.setTotal_amount(cartDTO.getTotal_amount());
        saleDTO.setListItems(cartDTO.getListItems());

        return saleDTO;

    }

    @Override
    public void saveSale(SaleDTO saleDTO) {
        Sale sale = new Sale();
        //Long id_cart = shoppingCartClient.saveCart(saleDTO.getCartDTO()).getBody();
        sale.setId_cart(saleDTO.getId_cart());
        sale.setCreate_at(LocalDateTime.now());

        saleRepository.save(sale);

    }
}
