package cz.ferdo.equiflow.repository;

import cz.ferdo.equiflow.entity.StockEntity;
import cz.ferdo.equiflow.model.Period;
import cz.ferdo.equiflow.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockJpaRepository extends JpaRepository<StockEntity, Long> {

    Optional<StockEntity> findByProviderAndTickerAndPeriod(
            Provider provider,
            String ticker,
            Period period
    );
}
