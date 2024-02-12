package com.object.mapper.service;

import com.object.mapper.model.Customer;

public interface CustomerService {

    Customer getCustomer(int id);

    void addCustomer(String firstName, String lastName, String email, String number) throws CorrectDataException;

    void deleteCustomer(int id);
}
