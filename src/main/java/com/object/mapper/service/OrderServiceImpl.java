package com.object.mapper.service;

import com.object.mapper.model.Customer;
import com.object.mapper.model.Order;
import com.object.mapper.model.Product;
import com.object.mapper.model.Status;
import com.object.mapper.repository.CustomerRepository;
import com.object.mapper.repository.OrderRepository;
import com.object.mapper.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private final OrderRepository orderRepository;
    @Autowired
    private final CustomerServiceImpl customerService;
    @Autowired
    private final ProductServiceImpl productService;

    public OrderServiceImpl(OrderRepository orderRepository,
                            CustomerServiceImpl customerService,
                            ProductServiceImpl productService) {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
        this.productService = productService;
    }

    @Override
    public Order getOrderInfo(int id) {
        return orderRepository.getOrderByOrderId(id);
    }

    @Override
    public Order createOrder(Order order) throws CorrectDataException {
        if (order.getShippingAddress() == null || order.getShippingAddress().isEmpty()) {
            throw new CorrectDataException("Поле \"Адрес заказа\" не может быть пустым");
        } else order.setShippingAddress(order.getShippingAddress());
        if (order.getCustomer() == null) {
            throw new CorrectDataException("Покупатель не указан");
        } else {
            Customer customer = order.getCustomer();
            order.setCustomer(customer);
            customerService.addCustomer(customer.getFirstName(),customer.getLastName(),
                    customer.getEmail(),customer.getContactNumber());
        }
        if (order.getTotalPrice() < 0) {
            throw new CorrectDataException("Общая стоимость заказа не может быть отрицательной");
        } else order.setTotalPrice(order.getTotalPrice());

        switch (order.getOrderStatus().toString()) {
            case "ACCEPTED":
                order.setOrderStatus(Status.ACCEPTED);
                break;
            case "PROCESSED":
                order.setOrderStatus(Status.PROCESSED);
                break;
            case "COMPLETED":
                order.setOrderStatus(Status.COMPLETED);
                break;
            default:
                throw new CorrectDataException("Определите статус заказа: ACCEPTED, PROCESSED или COMPLETED");
        }
        order.setProducts(order.getProducts());
        for (Product product : order.getProducts()) {
           productService.createProduct(product);
        }
        orderRepository.save(order);
        return order;
    }
}