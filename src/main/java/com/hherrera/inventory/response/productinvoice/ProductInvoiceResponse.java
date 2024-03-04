package com.hherrera.inventory.response.productinvoice;

import com.hherrera.inventory.model.ProductInvoice;
import lombok.Data;

import java.util.List;

@Data
public class ProductInvoiceResponse {
    private List<ProductInvoice> productInvoice;
}
