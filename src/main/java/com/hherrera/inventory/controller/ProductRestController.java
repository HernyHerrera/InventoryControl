package com.hherrera.inventory.controller;

import com.hherrera.inventory.model.Product;
import com.hherrera.inventory.pdf.StockPdf;
import com.hherrera.inventory.response.product.ProductResponseData;
import com.hherrera.inventory.response.product.ProductResponseRest;
import com.hherrera.inventory.services.product.IProductService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/inventory")
public class ProductRestController {
    @Autowired
    private IProductService service;
    /**
     * Get all the products
     * @return response
     * */
    @GetMapping("/products")
    public ResponseEntity<ProductResponseRest> serchProducts(){
        ResponseEntity<ProductResponseRest> response = service.search();
        return response;
    }
    /**
     * Save product
     * @param product
     * @return response
     * */
    @PostMapping("/products")
    public ResponseEntity<ProductResponseRest> save(@RequestBody Product product){
        ResponseEntity<ProductResponseRest> response = service.save(product);
        return response;
    }
    /**
     * Get product by code
     * @param code
     * @return response
     * */
    @GetMapping("/getProduct")
    @ResponseBody
    public ResponseEntity<Product> getListProducts( Integer code) {
        Product result = service.getProducts(code);
        if(result != null){
            return ResponseEntity.accepted().body(result);
        }else{
            return new ResponseEntity("error", HttpStatus.NOT_FOUND);
        }

    }
    @PutMapping("/updateQuantity")
    @ResponseBody
    public ResponseEntity<Integer> updateQuantity(@RequestBody Product product) {
        Boolean result = service.updateQuantity(product.getCode(), product.getStock());
        int codeResult = 0;
        if(result){
            codeResult = 1;
            return ResponseEntity.accepted().body(codeResult);
        }else {
            return new ResponseEntity(codeResult, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    /**
     * Get all the products
     * @return response
     * */
    @GetMapping("/productslist")
    @ResponseBody
    public ResponseEntity<List<ProductResponseData>> getListProducts(Long id, Integer code) {
        List<ProductResponseData> result = service.getProducts(id, code);
        if(result != null){
            return ResponseEntity.accepted().body(result);
        }else{
            return new ResponseEntity("error", HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/productslist/pdf")
    @ResponseBody
    public void exportReportPdf(HttpServletResponse response, Long id, Integer code) throws IOException {
        response.setContentType("application/pdf");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String today = format.format(new Date());
        String header = "Content-Disposition";
        String value = "attachment; filename=Report_" + today+".pdf";
        response.setHeader(header, value);
        List<ProductResponseData> products = service.getProducts(id, code);

        StockPdf exportNewPdf = new StockPdf(products);

        exportNewPdf.exportReport(response);

    }


    /**
     * Update product
     * @param product
     * @param id
     * @return response
     * */
    @PutMapping("/products/{id}")
    public ResponseEntity<ProductResponseRest> update(@RequestBody Product product,@PathVariable Long id){
        ResponseEntity<ProductResponseRest> response = service.update(product, id);
        return response;
    }
    /**
     * Delete product
     * @param id
     * @return response
     * */
    @DeleteMapping("/products/{id}")
    public ResponseEntity<ProductResponseRest> delete(@PathVariable Long id){
        ResponseEntity<ProductResponseRest> response = service.deleteById(id);
        return response;
    }

}
