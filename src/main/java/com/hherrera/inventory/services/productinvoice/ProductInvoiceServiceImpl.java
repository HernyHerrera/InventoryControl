package com.hherrera.inventory.services.productinvoice;

import com.hherrera.inventory.dao.IProductInvoiceDao;
import com.hherrera.inventory.model.ProductInvoice;
import com.hherrera.inventory.response.productinvoice.ProductInvoiceResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductInvoiceServiceImpl implements IProductInvoiceService{
    @Autowired
    private IProductInvoiceDao productInvoiceDao;

    /** search all productInvoices  **/
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ProductInvoiceResponseRest> search() {
        ProductInvoiceResponseRest response = new ProductInvoiceResponseRest();
        try{
            List<ProductInvoice> productInvoice = (List<ProductInvoice>) productInvoiceDao.findAll();
            response.getProductInvoiceResponse().setProductInvoice(productInvoice);
            response.setMetadata("Respuesta OK", "00", "Respuesta exitosa");

        }catch (Exception exception){
            response.setMetadata("Respuesta Error", "-1", "Error en consulta");
            exception.getStackTrace();
            return new ResponseEntity<ProductInvoiceResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductInvoiceResponseRest>(response, HttpStatus.OK);
    }

    /** save productInvoice  **/
    @Override
    @Transactional
    public ResponseEntity<ProductInvoiceResponseRest> save(ProductInvoice productInvoice) {
        ProductInvoiceResponseRest response = new ProductInvoiceResponseRest();
        List<ProductInvoice> list = new ArrayList<>();
        try{
            ProductInvoice productInvoiceSaved = productInvoiceDao.save(productInvoice);
            if(productInvoiceSaved != null){
                list.add(productInvoiceSaved);
                response.getProductInvoiceResponse().setProductInvoice(list);
                response.setMetadata("Respuesta OK", "00", "Productos ingresados en factura correctamente");
            }else {
                response.setMetadata("Respuesta Error", "-1", "Productos no guardados");
                return new ResponseEntity<ProductInvoiceResponseRest>(response, HttpStatus.BAD_REQUEST);
            }

        }catch (Exception exception){
            response.setMetadata("Respuesta Error", "-1", "Error al grabar productos");
            exception.getStackTrace();
            return new ResponseEntity<ProductInvoiceResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductInvoiceResponseRest>(response, HttpStatus.OK);
    }
}
