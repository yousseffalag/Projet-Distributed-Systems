package com.falag.youssef.ebankservice.model;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Customer {
    private Long id;
    private String name;
    private String email;
}
