package com.hherrera.inventory.controller;

import com.hherrera.inventory.response.provider.ProviderResponseRest;
import com.hherrera.inventory.services.provider.IProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
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
}
