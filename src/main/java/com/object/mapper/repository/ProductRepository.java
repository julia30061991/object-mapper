package com.object.mapper.repository;

import com.object.mapper.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Override
    List<Product> findAll();

    Product getProductByProductId(int id);

    @Override
    void delete(Product entity);
}
