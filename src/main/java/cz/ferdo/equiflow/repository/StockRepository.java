package cz.ferdo.equiflow.repository;

import cz.ferdo.equiflow.dto.MultiStockDTO;
import cz.ferdo.equiflow.model.Stock;

public interface StockRepository {

    Stock findBySymbol(String symbol);

    MultiStockDTO getAll();

    void loadStockFile(String fileName);
}
