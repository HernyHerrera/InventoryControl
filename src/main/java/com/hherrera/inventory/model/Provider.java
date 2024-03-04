package com.hherrera.inventory.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
@Data
@Entity
@Table(name = "provider")
public class Provider implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nif;
    @Column(name= "business_name")
    private String name;
    private String phone;
    private String email;
    private String address;
}
