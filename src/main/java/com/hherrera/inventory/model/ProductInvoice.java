package com.hherrera.inventory.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "product_invoice")
public class ProductInvoice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    @Column(name= "id_invoice")
    private Long idInvoice;
    @Column(name= "id_product")
    private Long idProduct;
}
