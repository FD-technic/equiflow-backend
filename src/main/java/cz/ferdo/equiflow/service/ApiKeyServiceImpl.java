package cz.ferdo.equiflow.service;

import cz.ferdo.equiflow.dto.ProviderApiKey;
import cz.ferdo.equiflow.entity.ProviderApiKeyEntity;
import cz.ferdo.equiflow.repository.ProviderApiKeyRepository;

public class ApiKeyServiceImpl implements ApiKeyService {

    private final ProviderApiKeyRepository providerApiKeyRepository;

    public ApiKeyServiceImpl(ProviderApiKeyRepository providerApiKeyRepository) {
        this.providerApiKeyRepository = providerApiKeyRepository;
    }

    @Override
    public String getApiKey(String provider) {

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

        entity.setProvider(apiKey.provider().toString());
        entity.setApiKey(apiKey.apiKey());

        providerApiKeyRepository.save(entity);

        return "API key for provider " + apiKey.provider() + " saved";
    }
}
