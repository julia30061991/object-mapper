package com.object.mapper.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.object.mapper.model.Order;
import com.object.mapper.model.Product;
import com.object.mapper.service.CorrectDataException;
import com.object.mapper.service.CustomerServiceImpl;
import com.object.mapper.service.OrderServiceImpl;
import com.object.mapper.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestContoller {

    @Autowired
    private final OrderServiceImpl orderService;
    @Autowired
    private final ProductServiceImpl productService;
    @Autowired
    private final CustomerServiceImpl customerService;

    private final ObjectMapper mapper = new ObjectMapper();


    public RestContoller(OrderServiceImpl orderService, ProductServiceImpl productService, CustomerServiceImpl customerService) {
        this.orderService = orderService;
        this.productService = productService;
        this.customerService = customerService;
    }

    @GetMapping("/products")
    public ResponseEntity<Object> getAllProducts() {
        try {
            List<Product> products = productService.getAllProducts();
            return new ResponseEntity<>(mapper.writeValueAsString(products), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/product/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") int id) {
        productService.deleteProduct(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Object> getProductInfo(@PathVariable("id") int id) {
        try {
            Product product = productService.getProduct(id);
            return new ResponseEntity<>(mapper.writeValueAsString(product), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (CorrectDataException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/product/create")
    public ResponseEntity<Object> createNewProduct(@RequestBody String json) {
        try {
            Product product = mapper.readValue(json, Product.class);
            productService.createProduct(product);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        } catch (JsonProcessingException | CorrectDataException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<Object> getOrderInfo(@PathVariable("id") int id) {
        try {
            Order order = orderService.getOrderInfo(id);
            mapper.registerModule(new JavaTimeModule());
            return new ResponseEntity<>(mapper.writeValueAsString(order), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/order/create")
    public ResponseEntity<Object> createNewOrder(@RequestBody String json) {
        try {

            Order order = mapper.readValue(json, Order.class);
            orderService.createOrder(order);
            return new ResponseEntity<>(order, HttpStatus.CREATED);
        } catch (JsonProcessingException | CorrectDataException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/customer/create")
    public ResponseEntity<Object> createNewCustomer(@RequestParam String firstName, String lastName,
                                                    String email, String phone) {
        try {
            customerService.addCustomer(firstName, lastName, email, phone);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (CorrectDataException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}