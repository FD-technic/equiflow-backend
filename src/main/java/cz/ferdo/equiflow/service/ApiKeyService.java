package cz.ferdo.equiflow.service;

import cz.ferdo.equiflow.dto.ProviderApiKey;
import org.springframework.stereotype.Service;

@Service
public interface ApiKeyService {
    String getApiKey(String provider);

    String setApiKey(ProviderApiKey apiKey);
}
