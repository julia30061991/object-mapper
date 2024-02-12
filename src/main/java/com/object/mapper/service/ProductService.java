package com.object.mapper.service;

import com.object.mapper.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product getProduct(int id) throws CorrectDataException;

    void updateProduct(int id, String name, String description, double price, int quantity) throws CorrectDataException;

    void deleteProduct(int id);

    Product createProduct(Product product) throws CorrectDataException;
}
