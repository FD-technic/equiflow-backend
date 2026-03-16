package cz.ferdo.equiflow.model;

import java.util.List;

public class Portfolio {

    private final List<Stock> portfolio;

    public Portfolio(List<Stock> portfolio) {
        this.portfolio = List.copyOf(portfolio);
    }

}
