package com.hherrera.inventory.services.invoice;

import com.hherrera.inventory.dao.IInvoiceDao;
import com.hherrera.inventory.model.Invoice;
import com.hherrera.inventory.response.invoice.InvoiceResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
public class InvoiceServiceImpl implements IInvoiceService{
    @Autowired
    private IInvoiceDao invoiceDao;
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<InvoiceResponseRest> search() {
        InvoiceResponseRest response = new InvoiceResponseRest();
        try{
            List<Invoice> invoice = (List<Invoice>) invoiceDao.findAll();
            response.getInvoiceResponse().setInvoice(invoice);
            response.setMetadata("Respuesta OK", "00", "Respuesta exitosa");

        }catch (Exception exception){
            response.setMetadata("Respuesta Error", "-1", "Error en consulta");
            exception.getStackTrace();
            return new ResponseEntity<InvoiceResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<InvoiceResponseRest>(response, HttpStatus.OK);
    }
    @Override
    @Transactional
    public ResponseEntity<InvoiceResponseRest> save(Invoice invoice) {
        InvoiceResponseRest response = new InvoiceResponseRest();
        List<Invoice> list = new ArrayList<>();
        try{
            Invoice invoiceSaved = invoiceDao.save(invoice);
            if(invoiceSaved != null){
                list.add(invoiceSaved);
                response.getInvoiceResponse().setInvoice(list);
                response.setMetadata("Respuesta OK", "00", "Factura guardada");
            }else {
                response.setMetadata("Respuesta Error", "-1", "Factura no guardada");
                return new ResponseEntity<InvoiceResponseRest>(response, HttpStatus.BAD_REQUEST);
            }

        }catch (Exception exception){
            response.setMetadata("Respuesta Error", "-1", "Error al grabar factura");
            exception.getStackTrace();
            return new ResponseEntity<InvoiceResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<InvoiceResponseRest>(response, HttpStatus.OK);
    }

}
