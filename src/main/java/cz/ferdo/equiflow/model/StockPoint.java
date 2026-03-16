package cz.ferdo.equiflow.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class StockPoint {

    private final LocalDate date;
    private final BigDecimal price;


    // čistý konstruktor
    public StockPoint(LocalDate date, BigDecimal price) {
        this.date = date;
        this.price = price;
    }

    // pomocný pro seed data
    public StockPoint(String date, double price) {
        this.date = LocalDate.parse(date);
        this.price = BigDecimal.valueOf(price);
    }

    // pomocný pro Twelve Data
    public StockPoint(String date, String price) {
        this.date = LocalDate.parse(date);
        this.price = new BigDecimal(price);
    }

    // ======================
    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getPrice() { return price; }
}
