package cz.ferdo.equiflow.provider;

import cz.ferdo.equiflow.dto.StockDTO;
import cz.ferdo.equiflow.dto.StockQuery;

public interface StockDataProvider {

    StockDTO parseStock(String json);

    String fetchRawJson(StockQuery query);
}
