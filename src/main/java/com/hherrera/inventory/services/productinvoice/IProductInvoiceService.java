package com.hherrera.inventory.services.productinvoice;

import com.hherrera.inventory.model.ProductInvoice;
import com.hherrera.inventory.response.productinvoice.ProductInvoiceResponseRest;
import org.springframework.http.ResponseEntity;

public interface IProductInvoiceService {

    /** search all productInvoices  **/
    public ResponseEntity<ProductInvoiceResponseRest> search();
    /** save productInvoice **/
    public ResponseEntity<ProductInvoiceResponseRest> save(ProductInvoice productInvoice);
}
