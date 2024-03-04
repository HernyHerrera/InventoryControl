package com.hherrera.inventory.response.productinvoice;

import com.hherrera.inventory.response.ResponseRest;
import lombok.Data;

@Data
public class ProductInvoiceResponseRest extends ResponseRest {
    private ProductInvoiceResponse productInvoiceResponse = new ProductInvoiceResponse();
}
