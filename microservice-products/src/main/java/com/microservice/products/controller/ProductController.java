package com.microservice.products.controller;
import com.microservice.products.model.Product;
import com.microservice.products.service.IServiceProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    IServiceProduct serviceProduct;

    /*@Value("{server.port}")
    private int serverPort;*/

    @GetMapping(value = "/get_all")
    public ResponseEntity<List<Product>> getAllProducts(){

        List<Product> listProducts = serviceProduct.getAllProducts();

        //System.out.println("------- Estoy en el puerto: " + serverPort);

        return ResponseEntity.ok(listProducts);

    }

    @GetMapping(value = "/get/{id_product}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id_product){

        Product product = serviceProduct.getProductById(id_product);
        return ResponseEntity.ok(product);
    }
}
