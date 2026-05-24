package cz.ferdo.equiflow.provider;

import cz.ferdo.equiflow.dto.StockQuery;
import cz.ferdo.equiflow.model.Stock;

public interface StockDataProvider {

    Stock fetchStock(String json);

    String fetchRawJson(StockQuery query);
}
