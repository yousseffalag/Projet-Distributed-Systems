package com.falag.youssef.customerservice;

import com.falag.youssef.customerservice.entities.Customer;
import com.falag.youssef.customerservice.services.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner start(CustomerService customerService) {
        return args -> {
            List<String> names = List.of("youssef", "imane", "omar", "ali", "fatima");
            names.forEach(name -> {
                customerService.createCustomer(
                        Customer.builder()
                                .name(name)
                                .email(name + "@gmail.com")
                                .build()
                );
            });
        };
    }
}
