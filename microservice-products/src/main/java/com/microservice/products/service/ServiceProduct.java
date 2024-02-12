package com.microservice.products.service;

import com.microservice.products.model.Product;
import com.microservice.products.repository.IRepositoryProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceProduct implements IServiceProduct {

    @Autowired
    IRepositoryProduct repositoryProduct;

    @Override
    public List<Product> getAllProducts() {
        List<Product> listProducts = repositoryProduct.findAll();
        return listProducts;
    }

    @Override
    public Product getProductById(Long id_product) {
        Product product = repositoryProduct.findById(id_product).orElse(null);
        return product;
    }

    @Override
    public void saveProduct(Product product) {
        repositoryProduct.save(product);
    }

    @Override
    public void deleteProduct(Long id_product) {
        repositoryProduct.deleteById(id_product);
    }

    @Override
    public void editProduct(Long id_product, Product product) {
        Product product_found = getProductById(id_product);

        product_found.setName(product_found.getName());
        product_found.setMake(product_found.getMake());
        product_found.setPrice(product_found.getPrice());

        saveProduct(product_found);
    }
}
