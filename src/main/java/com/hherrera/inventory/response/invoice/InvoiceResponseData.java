package com.hherrera.inventory.response.invoice;

import com.hherrera.inventory.response.ResponseRest;
import lombok.Data;

import java.util.Date;
@Data
public class InvoiceResponseData extends ResponseRest {

    private int invoiceNumber;
    private Date emissionDate;
    private String businessName;
    public InvoiceResponseData() {
    }
}
