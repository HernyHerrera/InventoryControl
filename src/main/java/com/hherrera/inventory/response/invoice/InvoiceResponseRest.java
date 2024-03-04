package com.hherrera.inventory.response.invoice;

import com.hherrera.inventory.response.ResponseRest;
import lombok.Data;

@Data
public class InvoiceResponseRest extends ResponseRest {
    private InvoiceResponse invoiceResponse = new InvoiceResponse();
}
