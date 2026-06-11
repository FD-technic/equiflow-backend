package cz.ferdo.equiflow.model;

import cz.ferdo.equiflow.dto.StockDTO;

import java.util.List;

public class Portfolio {

    private final List<StockDTO> portfolio;

    public Portfolio(List<StockDTO> portfolio) {
        this.portfolio = List.copyOf(portfolio);
    }

}
