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
    @Column(name= "invoice_number")
    private Integer invoiceNumber;
    @Column(name= "product_code")
    private Integer productCode;
}
