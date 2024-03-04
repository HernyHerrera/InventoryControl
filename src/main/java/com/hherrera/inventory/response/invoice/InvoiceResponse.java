package com.hherrera.inventory.response.invoice;

import com.hherrera.inventory.model.Invoice;
import lombok.Data;

import java.util.List;

@Data
public class InvoiceResponse {
    private List<Invoice> invoice;
}
