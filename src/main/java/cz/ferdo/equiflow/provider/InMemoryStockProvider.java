package cz.ferdo.equiflow.provider;

import cz.ferdo.equiflow.dto.StockQuery;
import cz.ferdo.equiflow.model.Stock;
import cz.ferdo.equiflow.repository.StockRepository;
import org.springframework.web.client.RestClient;

public class InMemoryStockProvider implements StockDataProvider{

    private final StockRepository repository;

    public InMemoryStockProvider(StockRepository repository) {
        this.repository = repository;
    }

    @Override
    public Stock fetchStock(String json) {
        return null;
    }

    @Override
    public String fetchRawJson(StockQuery query) {
        RestClient client = RestClient.create();
        return null;
    }

}
