package cz.ferdo.equiflow.wrapper;

import cz.ferdo.equiflow.model.StockPoint;

import java.util.List;

public class StockResponse {

    private List<StockPoint> points;

    private String currency;

    private String ticker;

    public StockResponse() {}

    public List<StockPoint> getPoints() {
        return points;
    }

    public void setPoints(List<StockPoint> points) {
        this.points = points;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }
}
