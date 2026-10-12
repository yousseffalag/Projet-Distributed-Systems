package com.falag.youssef.customerservice.services;

import com.falag.youssef.customerservice.entities.Customer;
import com.falag.youssef.customerservice.repositories.CustomerRepository;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @McpTool(description = "Get All customers")
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @McpTool(description = "Find Customer By id")
    public Customer findCustomerById(@McpToolParam(description = "The customer id") Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Customer with id %s not found", id)));
    }

    @McpTool(description = "Save Customer")
    public Customer createCustomer(@McpToolParam(description = "The customer to save(name,email)") Customer customer) {
        return customerRepository.save(customer);
    }
}
