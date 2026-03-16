package cz.ferdo.equiflow.repository;

import cz.ferdo.equiflow.model.StockPoint;

import java.util.List;

public interface StockRepository {

    List<StockPoint> findBySymbol(String symbol);
}
