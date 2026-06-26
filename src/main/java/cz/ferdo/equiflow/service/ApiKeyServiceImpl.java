package cz.ferdo.equiflow.service;

import cz.ferdo.equiflow.dto.ProviderApiKey;
import cz.ferdo.equiflow.entity.ProviderApiKeyEntity;
import cz.ferdo.equiflow.model.Provider;
import cz.ferdo.equiflow.repository.ProviderApiKeyRepository;
import org.springframework.stereotype.Service;

@Service
public class ApiKeyServiceImpl implements ApiKeyService {

    private final ProviderApiKeyRepository providerApiKeyRepository;

    public ApiKeyServiceImpl(ProviderApiKeyRepository providerApiKeyRepository) {
        this.providerApiKeyRepository = providerApiKeyRepository;
    }

    @Override
    public String getApiKey(Provider provider) {

        return providerApiKeyRepository.findById(provider)
                .map(ProviderApiKeyEntity::getApiKey)
                .orElseThrow(() ->
                        new IllegalStateException(
                                "API key for provider "
                                        + provider
                                        + " not found"
                        ));
    }

    @Override
    public String setApiKey(ProviderApiKey apiKey) {

        ProviderApiKeyEntity entity = new ProviderApiKeyEntity();

        entity.setProvider(apiKey.provider());
        entity.setApiKey(apiKey.apiKey());

        providerApiKeyRepository.save(entity);

        return "API key for provider " + apiKey.provider() + " saved";
    }
}
