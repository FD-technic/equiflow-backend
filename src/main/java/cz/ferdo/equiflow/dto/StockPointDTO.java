package cz.ferdo.equiflow.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record StockPointDTO(
    LocalDate date,
    BigDecimal open,
    BigDecimal high,
    BigDecimal low,
    BigDecimal close,
    BigDecimal volume
) {

}
