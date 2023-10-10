package com.example.springsecurityl17.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "client_id")
    private String clientId;
    private String secret;
    @Column(name = "grant_type")
    private String grantType;
    private String scope;
}
