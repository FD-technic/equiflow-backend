package cz.ferdo.equiflow.dto;

import cz.ferdo.equiflow.model.Provider;

public record ProviderApiKey(
        String apiKey,
        Provider provider
) {}
