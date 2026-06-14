package cz.ferdo.equiflow.service;

import cz.ferdo.equiflow.dto.MultiStockDTO;
import cz.ferdo.equiflow.dto.StockDTO;
import cz.ferdo.equiflow.dto.StockQuery;
import cz.ferdo.equiflow.entity.StockEntity;
import cz.ferdo.equiflow.mapper.StockMapper;
import cz.ferdo.equiflow.provider.AlphaVantageProvider;
import cz.ferdo.equiflow.repository.StockJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    private final StockJpaRepository stockJpaRepository;
    private final AlphaVantageProvider alphaVantageProvider;
    private final StockMapper stockMapper;


    public StockServiceImpl(StockJpaRepository stockJpaRepository, AlphaVantageProvider alphaVantageProvider, StockMapper stockMapper) {
        this.stockJpaRepository = stockJpaRepository;
        this.alphaVantageProvider = alphaVantageProvider;
        this.stockMapper = stockMapper;
    }

    @Override
    public MultiStockDTO getAll() {

        List<StockDTO> stocks = stockJpaRepository.findAll()
                .stream()
                .map(stockMapper::toDTO)
                .toList();

        return new MultiStockDTO(stocks);
    }

    @Transactional
    @Override
    public StockDTO getAlphaVantageStock(StockQuery query) {
        StockEntity stockEntity = stockJpaRepository
                .findByTickerAndPeriod(query.ticker(), query.period())
                .orElse(null);

        StockDTO stock;

        if (stockEntity == null || !dataValid(stockEntity)) {
            try {
                stock = alphaVantageProvider.parseStock(alphaVantageProvider.fetchRawJson(query));
                if (stockEntity == null) {
                    stockEntity = stockMapper.toEntity(stock);
                } else {
                    stockMapper.updateEntity(stock, stockEntity);
                }

                stockEntity.setPeriod(query.safePeriod());

                stockJpaRepository.save(stockEntity);
                System.out.println("Loaded fresh points from AlphaVantage");
            } catch (Exception e) {
                System.out.println("AlphaVantage unavailable, using cached points.");

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

        return LocalDateTime.now().isBefore(stock.getUpdateAt().plusDays(1));

    }
}
