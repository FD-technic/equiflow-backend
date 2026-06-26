package cz.ferdo.equiflow.service;

import cz.ferdo.equiflow.dto.ProviderApiKey;
import cz.ferdo.equiflow.model.Provider;
import org.springframework.stereotype.Service;

@Service
public interface ApiKeyService {
    String getApiKey(Provider provider);

    String setApiKey(ProviderApiKey apiKey);
}
