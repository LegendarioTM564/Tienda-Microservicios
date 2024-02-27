package com.microservice.shoppingcart.service;

import com.microservice.shoppingcart.dto.CartDTO;
import com.microservice.shoppingcart.model.Cart;
import com.microservice.shoppingcart.model.CartItem;

import java.util.List;

public interface ICartService {

    public List<Cart> getAllCart();
    public Cart getCartById(Long id_cart);
    public Long saveCart(CartDTO cartDTO);
    public CartDTO getCartDtoById(Long id_cart);

    public void deletedItemByID(Long id_cart, Long id_item);

}
