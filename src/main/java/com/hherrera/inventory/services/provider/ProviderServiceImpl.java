package com.hherrera.inventory.services.provider;

import com.hherrera.inventory.dao.IProviderDao;
import com.hherrera.inventory.model.Product;
import com.hherrera.inventory.model.Provider;
import com.hherrera.inventory.response.provider.ProviderResponse;
import com.hherrera.inventory.response.provider.ProviderResponseRest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
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
    @PersistenceContext
    private EntityManager entityManager;

    /** search all providers  **/
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

    /** get provider by nif **/
    @Override
    @Transactional
    public Provider getProvider(String nif){
        Provider result = new Provider();

        try{
            String queryString = "select provider from Provider as provider where provider.nif = " + nif.toUpperCase();
            Query query= entityManager.createQuery(queryString);
            List<Provider> list = query.getResultList();
            if (list!=null && list.size()>0) {
                result = list.get(0);
            }
        }catch (Exception exception) {
            exception.getMessage();
        }finally{
            return result;
        }
    }
}
