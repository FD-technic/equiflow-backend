package cz.ferdo.equiflow.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ProviderApiKeyEntity {

    @Id
    private String provider;

    private String apiKey;

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
