package cz.ferdo.equiflow.repository;

import cz.ferdo.equiflow.entity.ProviderApiKeyEntity;
import cz.ferdo.equiflow.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderApiKeyRepository extends JpaRepository<ProviderApiKeyEntity, Provider> {
}
