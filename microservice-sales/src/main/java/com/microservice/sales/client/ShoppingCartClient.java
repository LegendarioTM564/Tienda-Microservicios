package com.microservice.sales.client;

import com.microservice.sales.dto.CartDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="microservice-shoppingcart")
public interface ShoppingCartClient {

    @GetMapping(value = "/cart/get/{id_cart}")
    public CartDTO getCartById(@PathVariable Long id_cart);

    @PostMapping(value = "/cart/save")
    public ResponseEntity<Long> saveCart(@RequestBody CartDTO cartDTO);

}
