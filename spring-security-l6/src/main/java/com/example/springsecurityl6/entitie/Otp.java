package com.example.springsecurityl6.entitie;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "otp")
@Entity
@Data
public class Otp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String otp;
}
