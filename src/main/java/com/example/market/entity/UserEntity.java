package com.example.market.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "User")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String password;
    private String phone;
    private String email;
    private String address;
}
