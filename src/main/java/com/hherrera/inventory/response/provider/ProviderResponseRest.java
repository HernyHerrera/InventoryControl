package com.hherrera.inventory.response.provider;

import com.hherrera.inventory.response.ResponseRest;
import lombok.Data;

@Data
public class ProviderResponseRest extends ResponseRest {
    private ProviderResponse providerResponse = new ProviderResponse();
}
