package com.microservice.sales.client;

import com.microservice.sales.dto.CartDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="microservice-shoppingcart")
public interface ShoppingCartClient {

    @GetMapping(value = "/cart/get/{id_cart}")
    public CartDTO getCartById(@PathVariable Long id_cart);


}
