package com.microservice.shoppingcart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartItemDTO {

    private Long id_product;
    private String name_product;
    private double price_product;
    private int quantity;
    private double total_amount;

}
