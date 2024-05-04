package com.hherrera.inventory.controller;

import com.hherrera.inventory.model.Invoice;
import com.hherrera.inventory.response.invoice.InvoiceResponseData;
import com.hherrera.inventory.response.invoice.InvoiceResponseRest;
import com.hherrera.inventory.services.invoice.IInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins={"*"})
@RestController
@RequestMapping("/inventory")
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
     * Get all the invoices
     * @return response
     * @param invoice
     * @param nif
     * */
    @GetMapping("/invoiceslist")
    @ResponseBody
    public ResponseEntity<List<InvoiceResponseData>> getListInvoices(Integer invoice, String nif) {
        List<InvoiceResponseData> result = service.getInvoices(invoice, nif);
        if(result != null){
            return ResponseEntity.accepted().body(result);
        }else{
            return new ResponseEntity("error", HttpStatus.NOT_FOUND);
        }

    }
    /**
     * Get all the invoices
     * @return response
     * * @param invoiceNumber
     * */
    @GetMapping("/invoices/{invoiceNumber}")
    @ResponseBody
    public ResponseEntity<List<InvoiceResponseData>> getInvoiceByNumber(@PathVariable  Integer invoiceNumber) {
        List<InvoiceResponseData> result = service.getInvoices(invoiceNumber, null);
        if(result != null){
            return ResponseEntity.accepted().body(result);
        }else{
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }

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
