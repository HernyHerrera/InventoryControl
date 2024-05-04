package com.hherrera.inventory.services.invoice;

import com.hherrera.inventory.model.Invoice;
import com.hherrera.inventory.response.invoice.InvoiceResponseData;
import com.hherrera.inventory.response.invoice.InvoiceResponseRest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IInvoiceService {
    /** search all invoices **/
    public ResponseEntity<InvoiceResponseRest> search();

    /** get invoice by invoiceNumber / by nif  **/
    public List<InvoiceResponseData> getInvoices(Integer invoiceNumber, String nif);

    /** save invoice **/
    public ResponseEntity<InvoiceResponseRest> save(Invoice invoice);

}
