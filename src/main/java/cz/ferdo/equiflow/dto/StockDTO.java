package cz.ferdo.equiflow.dto;

import java.util.List;

public record StockDTO(
        String ticker,
        String currency,
        List<StockPointDTO> data
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
    public List<StockPointDTO> data() {
        return data;
    }
}