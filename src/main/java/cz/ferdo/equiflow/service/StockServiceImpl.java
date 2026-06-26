package cz.ferdo.equiflow.service;

import cz.ferdo.equiflow.dto.StockDTO;
import cz.ferdo.equiflow.dto.StockQuery;
import cz.ferdo.equiflow.entity.StockEntity;
import cz.ferdo.equiflow.mapper.StockMapper;
import cz.ferdo.equiflow.provider.AlphaVantageProvider;
import cz.ferdo.equiflow.provider.YahooProvider;
import cz.ferdo.equiflow.repository.StockJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static cz.ferdo.equiflow.model.Provider.ALPHAVANTAGE;
import static cz.ferdo.equiflow.model.Provider.YAHOO;

@Service
public class StockServiceImpl implements StockService {

    private final StockJpaRepository stockJpaRepository;
    private final AlphaVantageProvider alphaVantageProvider;
    private final YahooProvider yahooProvider;
    private final StockMapper stockMapper;


    public StockServiceImpl(StockJpaRepository stockJpaRepository, AlphaVantageProvider alphaVantageProvider, YahooProvider yahooProvider, StockMapper stockMapper) {
        this.stockJpaRepository = stockJpaRepository;
        this.alphaVantageProvider = alphaVantageProvider;
        this.yahooProvider = yahooProvider;
        this.stockMapper = stockMapper;
    }



    @Transactional
    @Override
    public StockDTO findByQuery(StockQuery query) {
        StockEntity stockEntity = stockJpaRepository
                .findByProviderAndTickerAndPeriod(query.provider(), query.ticker(), query.period())
                .orElse(null);

        String provider = query.safeProvider().getValue();
        StockDTO stock;

        if (stockEntity == null || !dataValid(stockEntity)) {
            try {
                switch (query.safeProvider()) {
                    case ALPHAVANTAGE -> stock = alphaVantageProvider.load(query);
                    case YAHOO -> stock = yahooProvider.load(query);
                    default ->
                            throw new IllegalArgumentException(
                                    "Unsupported provider: " + query.safeProvider()
                            );
                }

                if (stockEntity == null) {
                    stockEntity = stockMapper.toEntity(stock);
                } else {
                    stockMapper.updateEntity(stock, stockEntity);
                }

                stockEntity.setProvider(query.safeProvider());
                stockEntity.setPeriod(query.safePeriod());

                stockJpaRepository.save(stockEntity);
                System.out.println("Loaded fresh points from " + provider);
            } catch (Exception e) {
                System.out.println(provider + " unavailable, using cached points.");

                if (stockEntity != null) {
                    stock = stockMapper.toDTO(stockEntity);
                } else {
                    throw new RuntimeException(
                            "No cached data available", e
                    );
                }
            }
        } else {
            stock = stockMapper.toDTO(stockEntity);
            System.out.println("Saved data");
        }

        return stock;
    }




    private boolean dataValid(StockEntity stock) {

        return LocalDateTime.now().isBefore(stock.getUpdatedAt().plusDays(1));

    }
}
