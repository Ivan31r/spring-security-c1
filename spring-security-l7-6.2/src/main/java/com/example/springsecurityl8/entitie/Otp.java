package com.example.springsecurityl8.entitie;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "otps")
@Entity
@Data
public class Otp {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="article_gen", sequenceName="OTPS_SEQ")
    private Integer id;
    private String username;
    private String otp;
}
