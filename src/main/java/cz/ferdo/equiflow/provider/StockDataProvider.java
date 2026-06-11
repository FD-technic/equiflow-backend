package cz.ferdo.equiflow.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.ferdo.equiflow.dto.StockDTO;
import cz.ferdo.equiflow.dto.StockQuery;

public interface StockDataProvider {

    StockDTO parseStock(String json);

    String fetchRawJson(StockQuery query);
}
