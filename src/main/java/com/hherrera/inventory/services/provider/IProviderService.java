package com.hherrera.inventory.services.provider;

import com.hherrera.inventory.response.category.CategoryResponseRest;
import com.hherrera.inventory.response.provider.ProviderResponseRest;
import org.springframework.http.ResponseEntity;

public interface IProviderService {
    public ResponseEntity<ProviderResponseRest> search();
}
