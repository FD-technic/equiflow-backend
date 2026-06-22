package cz.ferdo.equiflow.repository;

import cz.ferdo.equiflow.entity.PortfolioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PortfolioRepository extends JpaRepository<PortfolioEntity, Long> {
    Optional<PortfolioEntity> findById(Long id);
}
