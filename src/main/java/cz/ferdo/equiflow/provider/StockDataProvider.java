package cz.ferdo.equiflow.provider;

import cz.ferdo.equiflow.dto.StockDTO;
import cz.ferdo.equiflow.dto.StockQuery;
import cz.ferdo.equiflow.model.Provider;

public interface StockDataProvider {

    Provider getProvider();

    StockDTO load(StockQuery query);

    StockDTO parseStock(String json);

    String fetchRawJson(StockQuery query);
}
