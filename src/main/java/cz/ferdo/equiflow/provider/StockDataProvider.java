package cz.ferdo.equiflow.provider;

import cz.ferdo.equiflow.model.StockPoint;

import java.util.List;

public interface StockDataProvider {
    // co to znamená?
    List<StockPoint> fetchStock(String ticker);
}
