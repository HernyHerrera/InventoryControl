package com.hherrera.inventory.response.product;

import com.hherrera.inventory.response.ResponseRest;
import lombok.Data;

@Data
public class ProductResponseRest extends ResponseRest {
    private ProductResponse productResponse = new ProductResponse();
}
