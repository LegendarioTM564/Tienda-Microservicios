package com.microservice.products.service;



import com.microservice.products.model.Product;

import java.util.List;

public interface IServiceProduct {

    public List<Product> getAllProducts();
    public Product getProductById(Long id_product);

    public void saveProduct(Product product);
    public void deleteProduct(Long id_product);
    public void editProduct(Long id_product, Product product);

}
