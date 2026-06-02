package cz.ferdo.equiflow.service;

import cz.ferdo.equiflow.dto.MultiStockDTO;
import cz.ferdo.equiflow.dto.StockQuery;
import cz.ferdo.equiflow.model.ProviderApiKey;
import cz.ferdo.equiflow.model.StockResponse;

public interface StockService {

    MultiStockDTO getAll();

    StockResponse getLocalData(String ticker, int days) ;

    StockResponse getAlphaVantageStock(StockQuery query);

    String setApiKey(ProviderApiKey apiKey);

    String getApiKey(String provider);
}
