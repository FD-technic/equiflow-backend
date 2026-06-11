package cz.ferdo.equiflow.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public record StockDTO(
        String ticker,
        String currency,
        List<StockPointDTO> points,
        LocalDateTime updateAt
) {
    @Override
    public List<StockPointDTO> points() {
        List<StockPointDTO> reversed = new ArrayList<>(points);
        Collections.reverse(reversed);
        return reversed;
    }
}