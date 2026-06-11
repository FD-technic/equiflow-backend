package cz.ferdo.equiflow.config;

import cz.ferdo.equiflow.model.Provider;

public record ProviderApiKey(
        String apiKey,
        Provider provider
) {}
