package cz.ferdo.equiflow.service;

import cz.ferdo.equiflow.dto.MultiStockDTO;
import cz.ferdo.equiflow.dto.StockDTO;
import cz.ferdo.equiflow.dto.StockQuery;
import cz.ferdo.equiflow.model.ProviderApiKey;
import cz.ferdo.equiflow.model.Stock;

public interface StockService {

    MultiStockDTO getAll();

    StockDTO getStockData(String ticker, int days) ;

    Stock getLiveTicker(StockQuery query);

    String setApiKey(ProviderApiKey apiKey);
}
