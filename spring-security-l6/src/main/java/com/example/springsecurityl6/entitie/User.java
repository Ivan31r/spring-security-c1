package com.example.springsecurityl6.entitie;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "users")
@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
}
