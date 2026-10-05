package com.falag.youssef.customerservice.repositories;

import com.falag.youssef.customerservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
