package com.hherrera.inventory.response.product;

import com.hherrera.inventory.model.Product;
import lombok.Data;

import java.util.List;

@Data
public class ProductResponse {
    private List<Product> product;
}
