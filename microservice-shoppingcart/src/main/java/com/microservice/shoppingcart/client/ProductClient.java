package com.microservice.shoppingcart.client;

import com.microservice.shoppingcart.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "microservice-products")
public interface ProductClient {

    @GetMapping(value = "/products/get/{id_product}")
    public ProductDTO getProductsById(@PathVariable Long id_product);





}
