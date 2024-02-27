package com.microservice.shoppingcart.controller;


import com.microservice.shoppingcart.dto.CartDTO;
import com.microservice.shoppingcart.model.Cart;
import com.microservice.shoppingcart.service.ICartService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    ICartService cartService;

    @GetMapping(value = "/get_all")
    public ResponseEntity<List<Cart>> getAllCart(){
        List<Cart> listCart = cartService.getAllCart();
        return ResponseEntity.ok(listCart);
    }



    @GetMapping(value = "/get/{id_cart}")
    @CircuitBreaker(name="microservice-products", fallbackMethod = "fallBackGetCartById")
    @Retry(name= "microservice-products")
    public ResponseEntity<CartDTO> getCartByID(@PathVariable Long id_cart){
        CartDTO cartDTO = cartService.getCartDtoById(id_cart);
        return ResponseEntity.ok(cartDTO);
    }


    @CircuitBreaker(name="microservice-products", fallbackMethod = "fallBackSaveCart")
    @Retry(name= "microservice-products")
    @PostMapping(value = "/save")
    public ResponseEntity<Long> saveCart(@RequestBody CartDTO cartDTO){

        Long idCart = cartService.saveCart(cartDTO);
        return ResponseEntity.ok(idCart);
    }

    @DeleteMapping(value ="{id_cart}/delete/item/{id_item}" )
    public ResponseEntity<String> deletedItemById(@PathVariable Long id_cart, @PathVariable Long id_item){

        cartService.deletedItemByID(id_cart,id_item);

        return ResponseEntity.ok("Item eliminado");
    }

    private ResponseEntity<CartDTO> fallBackGetCartById(Long id_cart, Throwable throwable){
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
    }

    private ResponseEntity<String> fallBackSaveCart(CartDTO cartDTO, Throwable throwable){
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
    }


}
