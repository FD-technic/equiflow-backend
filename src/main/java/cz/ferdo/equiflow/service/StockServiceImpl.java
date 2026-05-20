package cz.ferdo.equiflow.service;

import cz.ferdo.equiflow.dto.MultiStockDTO;
import cz.ferdo.equiflow.dto.StockDTO;
import cz.ferdo.equiflow.dto.StockPointDTO;
import cz.ferdo.equiflow.dto.StockQuery;
import cz.ferdo.equiflow.mapper.StockPointMapper;
import cz.ferdo.equiflow.model.Stock;
import cz.ferdo.equiflow.model.StockPoint;
import cz.ferdo.equiflow.provider.AlphaVantageProvider;
import cz.ferdo.equiflow.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    private final AlphaVantageProvider alphaVantageProvider;


    public StockServiceImpl(StockRepository stockRepository, AlphaVantageProvider alphaVantageProvider) {
        this.stockRepository = stockRepository;
        this.alphaVantageProvider = alphaVantageProvider;
    }

    @Override
    public MultiStockDTO getAll() {

        return stockRepository.getAll();
    }

    @Override
    public StockDTO getStockData(String ticker, int days) {
        Stock stockData = stockRepository.findBySymbol(ticker.toUpperCase());

        return new StockDTO(ticker, "USD", stockData.getPoints()
                .stream()
                .map(StockPointMapper::toDTO)
                .toList());
    }

    @Override
    public Stock getLiveTicker(StockQuery query) {
        return alphaVantageProvider.fetchStock(query);
    }
}
