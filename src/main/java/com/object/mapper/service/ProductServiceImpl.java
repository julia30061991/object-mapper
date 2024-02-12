package com.object.mapper.service;

import com.object.mapper.model.Product;
import com.object.mapper.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(int id) throws CorrectDataException {
        Product product = productRepository.getProductByProductId(id);
        if (product != null) {
            return product;
        } else {
            throw new CorrectDataException("Продукт с таким идентификатором отсутствует в базе");
        }
    }

    @Override
    public void updateProduct(int id, String name, String description, double price, int quantity) throws CorrectDataException {
        Product product = productRepository.getProductByProductId(id);
        if (name == null || name.isEmpty()) {
            product.setName(product.getName());
        } else product.setName(name);
        if (description == null || description.isEmpty()) {
            product.setDescription(product.getDescription());
        } else product.setDescription(description);
        if (price <= 0) {
            throw new CorrectDataException("Цена не может быть ниже или равна 0, " +
                    "проверьте корректность вводимых данных");
        } else product.setPrice(price);
        if (quantity < 0) {
            throw new CorrectDataException("Количество товара на складе не может быть отрицательным числом");
        } else product.setQuantityInStock(quantity);

        productRepository.save(product);
    }

    @Override
    public void deleteProduct(int id) {
        Product product = productRepository.getProductByProductId(id);
        productRepository.delete(product);
    }

    //метод для реста с получением json-строки
    @Override
    public Product createProduct(Product product) throws CorrectDataException {
        if (product.getName() == null || product.getName().isEmpty()) {
            throw new CorrectDataException("Поле \"Название товара\" не может быть пустым");
        } else product.setName(product.getName());
        if (product.getPrice() <= 0) {
            throw new CorrectDataException("Цена не может быть ниже или равна 0, " +
                    "проверьте корректность вводимых данных");
        } else product.setPrice(product.getPrice());
        if (product.getQuantityInStock() < 0) {
            throw new CorrectDataException("Количество товара на складе не может быть отрицательным числом");
        } else product.setQuantityInStock(product.getQuantityInStock());

        product.setDescription(product.getDescription());
        productRepository.save(product);
        return product;
    }
}