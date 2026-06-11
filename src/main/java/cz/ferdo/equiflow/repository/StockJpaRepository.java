package cz.ferdo.equiflow.repository;

import cz.ferdo.equiflow.entity.StockEntity;
import cz.ferdo.equiflow.model.Period;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockJpaRepository extends JpaRepository<StockEntity, Long> {

    Optional<StockEntity> findByTickerAndPeriod(
            String ticker,
            Period period
    );
}
