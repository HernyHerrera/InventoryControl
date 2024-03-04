package com.hherrera.inventory.services.provider;

import com.hherrera.inventory.dao.IProviderDao;
import com.hherrera.inventory.model.Provider;
import com.hherrera.inventory.response.provider.ProviderResponse;
import com.hherrera.inventory.response.provider.ProviderResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProviderServiceImpl implements IProviderService{
    @Autowired
    private IProviderDao providerDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ProviderResponseRest> search(){
        ProviderResponseRest response = new ProviderResponseRest();
        try{
            List<Provider> provider = (List<Provider>) providerDao.findAll();
            response.getProviderResponse().setProvider(provider);
            response.setMetadata("Respuesta OK", "00", "Respuesta exitosa");

        }catch (Exception exception){
            response.setMetadata("Respuesta Error", "-1", "Error en consulta");
            exception.getStackTrace();
            return new ResponseEntity<ProviderResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProviderResponseRest>(response, HttpStatus.OK);
        }


}
