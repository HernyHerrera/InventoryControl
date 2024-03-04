package com.hherrera.inventory.model;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name= "product_code")
    private int code;
    @Column(name= "product_name")
    private String name;
    @Column(name= "product_description")
    private String description;
    private Double price;
    private int stock;
    @Column(name= "id_category")
    private Long idCategory;
    @Column(name= "id_user")
    private Long idUser;

}
