package com.hherrera.inventory.services.productinvoice;

import com.hherrera.inventory.model.ProductInvoice;
import com.hherrera.inventory.response.productinvoice.ProductInvoiceResponseRest;
import org.springframework.http.ResponseEntity;

public interface IProductInvoiceService {
    public ResponseEntity<ProductInvoiceResponseRest> search();
    public ResponseEntity<ProductInvoiceResponseRest> save(ProductInvoice productInvoice);
}
