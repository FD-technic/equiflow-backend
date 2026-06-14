package cz.ferdo.equiflow.service;

import cz.ferdo.equiflow.dto.MultiStockDTO;
import cz.ferdo.equiflow.dto.StockDTO;
import cz.ferdo.equiflow.dto.StockQuery;
import org.springframework.stereotype.Service;

@Service
public interface StockService {

    MultiStockDTO getAll();

    StockDTO getAlphaVantageStock(StockQuery query);
}
