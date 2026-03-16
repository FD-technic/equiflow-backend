package cz.ferdo.equiflow.provider;

import cz.ferdo.equiflow.model.StockPoint;
import cz.ferdo.equiflow.repository.StockRepository;

import java.util.List;

public class InMemoryStockProvider implements StockDataProvider{

    private final StockRepository repository;

    public InMemoryStockProvider(StockRepository repository) {
        this.repository = repository;
    }
    @Override
    public List<StockPoint> fetchStock(String ticker) {
        return repository.findBySymbol(ticker);
    }
}
