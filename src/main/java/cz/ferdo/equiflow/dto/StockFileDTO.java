package cz.ferdo.equiflow.dto;

import java.util.List;

public record StockFileDTO(
        String ticker,
        String currency,
        List<StockPointDTO> points
) {
    @Override
    public String ticker() {
        return ticker;
    }

    @Override
    public String currency() {
        return currency;
    }

    @Override
    public List<StockPointDTO> points() {
        return points;
    }
}