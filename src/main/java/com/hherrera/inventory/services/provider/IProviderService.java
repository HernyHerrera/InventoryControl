package com.hherrera.inventory.services.provider;

import com.hherrera.inventory.model.Provider;
import com.hherrera.inventory.response.provider.ProviderResponseRest;
import org.springframework.http.ResponseEntity;

public interface IProviderService {

    /** search all providers  **/
    public ResponseEntity<ProviderResponseRest> search();
    /** get provider by nif **/
    public Provider getProvider(String nif);
}
