package com.object.mapper.service;

import com.object.mapper.model.Customer;
import com.object.mapper.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final String REGEX_EMAIL = "([A-Za-z0-9-_.]+@[A-Za-z0-9-_]+(?:\\.[A-Za-z0-9]+)+)";
    @Autowired
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer getCustomer(int id) {
        return customerRepository.getCustomerByCustomerId(id);
    }

    @Override
    public void addCustomer(String firstName, String lastName, String email, String number) throws CorrectDataException{
        if(!email.matches(REGEX_EMAIL)) {
            throw new CorrectDataException("Невалидный email, проверьте корректность вводимых данных");
        }
        if (firstName.isEmpty() || lastName.isEmpty()) {
            throw new CorrectDataException("Поля \"Фамилия\" и \"Имя\" не могут быть пустыми");
        }
        Customer customer = new Customer(firstName, lastName, email, number);
        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(int id) {
        customerRepository.deleteById(id);
    }
}