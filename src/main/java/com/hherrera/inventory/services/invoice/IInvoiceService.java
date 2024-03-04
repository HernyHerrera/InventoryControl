package com.hherrera.inventory.services.invoice;

import com.hherrera.inventory.model.Invoice;
import com.hherrera.inventory.response.invoice.InvoiceResponseRest;
import org.springframework.http.ResponseEntity;

public interface IInvoiceService {
    public ResponseEntity<InvoiceResponseRest> search();
    public ResponseEntity<InvoiceResponseRest> save(Invoice invoice);
}
