package com.hherrera.inventory.response.product;

import com.hherrera.inventory.response.ResponseRest;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ProductResponseData extends ResponseRest {

    private Long id;
    private Long idCategory;
    private int code;
    private String category;
    private String nameProduct;
    private String description;
    private Double price;
    private int stock;
    public ProductResponseData() {
    }
}
