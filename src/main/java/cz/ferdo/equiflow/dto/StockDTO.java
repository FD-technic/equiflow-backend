package cz.ferdo.equiflow.dto;

import java.time.LocalDateTime;
import java.util.List;

public record StockDTO(
        String ticker,
        String currency,
        List<StockPointDTO> points,
        LocalDateTime updateAt
) {

}