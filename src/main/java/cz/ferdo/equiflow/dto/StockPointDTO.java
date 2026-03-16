package cz.ferdo.equiflow.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record StockPointDTO(
    LocalDate date,
    BigDecimal price
) {
    @Override
    public LocalDate date() {
        return date;
    }

    @Override
    public BigDecimal price() {
        return price;
    }
}
