package com.hherrera.inventory.controller;

import com.hherrera.inventory.model.Product;
import com.hherrera.inventory.model.Provider;
import com.hherrera.inventory.response.provider.ProviderResponseRest;
import com.hherrera.inventory.services.provider.IProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/inventory")
public class ProviderResController {
    @Autowired
    private IProviderService service;

    /**
     * Get all the providers
     * @return response
     * */
    @GetMapping("/providers")
    public ResponseEntity<ProviderResponseRest> serchProviders(){
        ResponseEntity<ProviderResponseRest> response = service.search();
        return response;
    }
    /**
     * Get provider by code
     * @param nif
     * @return result
     * */
    @GetMapping("/getProvider")
    @ResponseBody
    public ResponseEntity<Provider> getProvider(String nif) {
        Provider result = service.getProvider(nif);
        if(result != null){
            return ResponseEntity.accepted().body(result);
        }else{
            return new ResponseEntity("error", HttpStatus.NOT_FOUND);
        }

    }
}
