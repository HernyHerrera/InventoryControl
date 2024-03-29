package com.hherrera.inventory.controller;

import com.hherrera.inventory.model.ProductInvoice;
import com.hherrera.inventory.response.productinvoice.ProductInvoiceResponseRest;
import com.hherrera.inventory.services.productinvoice.IProductInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ProductInvoiceRestController {
    @Autowired
    private IProductInvoiceService service;

    /**
     * Get all productInvoices
     * @return response
     * */
    @GetMapping("/productinvoices")
    public ResponseEntity<ProductInvoiceResponseRest> serchProductInvoices(){
        ResponseEntity<ProductInvoiceResponseRest> response = service.search();
        return response;
    }
    @PostMapping("/productinvoices")
    public ResponseEntity<ProductInvoiceResponseRest> save(@RequestBody ProductInvoice productInvoice){
        ResponseEntity<ProductInvoiceResponseRest> response = service.save(productInvoice);
        return response;
    }
    /**
     * Update categogy
     * @param category
     * @param id
     * @return response
     * */

}
