package com.hherrera.inventory.controller;

import com.hherrera.inventory.model.Invoice;
import com.hherrera.inventory.response.invoice.InvoiceResponseRest;
import com.hherrera.inventory.services.invoice.IInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class InvoiceRestController {
    @Autowired
    private IInvoiceService service;

    /**
     * Get all the invoices
     * @return response
     * */
    @GetMapping("/invoices")
    public ResponseEntity<InvoiceResponseRest> serchInvoices(){
        ResponseEntity<InvoiceResponseRest> response = service.search();
        return response;
    }
    /**
     * Save invoice
     * @param invoice
     * @return response
     * */
    @PostMapping("/invoices")
    public ResponseEntity<InvoiceResponseRest> save(@RequestBody Invoice invoice){
        ResponseEntity<InvoiceResponseRest> response = service.save(invoice);
        return response;
    }
}
