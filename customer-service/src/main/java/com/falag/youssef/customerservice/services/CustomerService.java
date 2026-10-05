package com.falag.youssef.customerservice.services;

import com.falag.youssef.customerservice.entities.Customer;
import com.falag.youssef.customerservice.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer findCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Customer with id %s not found", id)));
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
}
