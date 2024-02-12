package com.object.mapper.service;

import com.object.mapper.model.Order;

public interface OrderService {

    Order getOrderInfo(int id);

    Order createOrder(Order order) throws CorrectDataException;
}