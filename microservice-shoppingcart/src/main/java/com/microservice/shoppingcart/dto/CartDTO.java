package com.microservice.shoppingcart.dto;

import com.microservice.shoppingcart.model.CartItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class CartDTO {
    private Long id_cart;
    private List<CartItemDTO> listItems = new ArrayList<>();
    private double total_amount;

}
