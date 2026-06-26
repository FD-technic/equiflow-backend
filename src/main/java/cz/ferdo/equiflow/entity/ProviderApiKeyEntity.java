package cz.ferdo.equiflow.entity;

import cz.ferdo.equiflow.model.Provider;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

@Entity(name = "provider_api_key")
public class ProviderApiKeyEntity {

    @Id
    @Enumerated(EnumType.STRING)
    private Provider provider;

    private String apiKey;

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
