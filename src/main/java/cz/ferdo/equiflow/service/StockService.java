package cz.ferdo.equiflow.service;

import cz.ferdo.equiflow.dto.StockDTO;


public interface StockService {

    StockDTO getStockData(String ticker, int days) ;
}
