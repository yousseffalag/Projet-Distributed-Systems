package com.falag.youssef.ebankservice.entities;

import com.falag.youssef.ebankservice.model.Customer;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.*;

import java.util.Date;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class BankAccount {
    @Id
    private String id;
    private double balance;
    private Date createdAt;
    private String type;
    private long customerId;

    @Transient
    private Customer customer;
}
