package com.hherrera.inventory.services.invoice;

import com.hherrera.inventory.dao.IInvoiceDao;
import com.hherrera.inventory.model.Invoice;
import com.hherrera.inventory.response.invoice.InvoiceResponseData;
import com.hherrera.inventory.response.invoice.InvoiceResponseRest;
import com.hherrera.inventory.response.product.ProductResponseData;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.type.descriptor.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class InvoiceServiceImpl implements IInvoiceService{
    @Autowired
    private IInvoiceDao invoiceDao;
    @PersistenceContext
    private EntityManager entityManager;

    /** search all invoices **/
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

    /** save invoice **/
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
    /** get invoice by invoiceNumber / by nif  **/
    @Override
    @Transactional
    public List<InvoiceResponseData> getInvoices(Integer invoiceNumber, String nif){
        List<InvoiceResponseData> list = null;
        try{
            //select invoice_number, emission_date, business_name from invoice, provider where invoice.id_provider = provider.id and invoice.invoice_number=502 and provider.nif="X456230"

            String queryString = "select invoice.invoiceNumber, invoice.emissionDate, provider.businessName from Invoice as invoice, Provider as provider where invoice.idProvider = provider.id";

            if (nif != null){
                queryString += " and provider.nif=" + nif;
            }
            if (invoiceNumber != null){
                queryString += " and invoice.invoiceNumber=" + invoiceNumber;
            }

            Query query= entityManager.createQuery(queryString);
            List<Object[]> rows = query.getResultList();
            if (rows!=null && rows.size()>0) {
                list = new ArrayList<>();

                for(Object[] row : rows){
                    InvoiceResponseData invoiceResponseData = new InvoiceResponseData();
                    invoiceResponseData.setInvoiceNumber(Integer.parseInt(row[0].toString()));
                    invoiceResponseData.setEmissionDate((Date) row[1]);
                    invoiceResponseData.setBusinessName(row[2].toString());


                    list.add(invoiceResponseData);

                }
            }
        }catch (Exception exception) {
            exception.getMessage();
        }finally{
            return list;
        }
    }

}
