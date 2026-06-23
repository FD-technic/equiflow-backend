package cz.ferdo.equiflow.dto;

import java.math.BigDecimal;

public record PositionDetailDTO(
        String ticker,
        long quantity,
        BigDecimal buyPrice,
        BigDecimal currentPrice,
        BigDecimal investedValue,
        BigDecimal currentValue,
        BigDecimal profit,
        BigDecimal profitPercent
) {
}
