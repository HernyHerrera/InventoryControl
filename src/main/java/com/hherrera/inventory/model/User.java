package com.hherrera.inventory.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "inv_user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dni;
    @Column(name= "password_user")
    private String password;
    @Column(name= "user_name")
    private String name;
    @Column(name= "last_name")
    private String lastName;
    private String email;
}
