package cz.ferdo.equiflow.dto;

import java.math.BigDecimal;

public class PositionDTO {

    private String ticker;
    private long quantity;
    private BigDecimal buyPrice;

    public PositionDTO(String ticker, long quantity, BigDecimal buyPrice) {
        this.ticker = ticker;
        this.quantity = quantity;
        this.buyPrice = buyPrice;
    }

    // === Getter / Setter

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(BigDecimal buyPrice) {
        this.buyPrice = buyPrice;
    }
}
