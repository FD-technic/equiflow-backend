package cz.ferdo.equiflow.repository;

import cz.ferdo.equiflow.entity.ProviderApiKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderApiKeyRepository extends JpaRepository<ProviderApiKeyEntity, String> {
}
