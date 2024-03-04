package com.hherrera.inventory.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
@Entity
@Table(name = "invoice")
public class Invoice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "invoice_number")
    private int number;
    @Column(name= "emission_date")
    private Date emissionDate;
    @Column(name= "cancel_date")
    private Date cancelDate;
    @Column(name= "id_user")
    private Long idUser;
    @Column(name= "id_provider")
    private Long idProvider;
}
