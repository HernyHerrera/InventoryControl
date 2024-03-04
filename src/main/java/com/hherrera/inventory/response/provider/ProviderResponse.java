package com.hherrera.inventory.response.provider;

import com.hherrera.inventory.model.Provider;
import lombok.Data;

import java.util.List;
@Data
public class ProviderResponse {
    private List<Provider> provider;
}
