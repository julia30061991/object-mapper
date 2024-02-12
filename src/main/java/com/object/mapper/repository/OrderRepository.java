package com.object.mapper.repository;

import com.object.mapper.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    Order getOrderByOrderId(int id);
}
