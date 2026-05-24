package cz.ferdo.equiflow.model;

import java.util.List;

public class Stock {

    private final List<StockPoint> points;
    private final String ticker;
    private final String currency;

    public Stock(String ticket, List<StockPoint> points, String currency) {
        this.points = List.copyOf(points);
        this.ticker = ticket;
        this.currency = currency;
    }

    public List<StockPoint> getPoints() {
        return points.reversed();
    }

    public String getTicker() {
        return ticker;
    }

    public String getCurrency() {
        return currency;
    }
}
