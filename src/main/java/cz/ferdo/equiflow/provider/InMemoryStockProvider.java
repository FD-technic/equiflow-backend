package cz.ferdo.equiflow.provider;

import cz.ferdo.equiflow.dto.StockQuery;
import cz.ferdo.equiflow.model.Stock;
import cz.ferdo.equiflow.repository.StockRepository;

public class InMemoryStockProvider implements StockDataProvider{

    private final StockRepository repository;

    public InMemoryStockProvider(StockRepository repository) {
        this.repository = repository;
    }

    @Override
    public Stock fetchStock(StockQuery query) {
        return repository.findBySymbol(query.ticker().toUpperCase());
    }
}
