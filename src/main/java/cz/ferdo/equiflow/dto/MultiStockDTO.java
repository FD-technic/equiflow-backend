package cz.ferdo.equiflow.dto;

import java.util.List;

public record MultiStockDTO(
        List<StockDTO> stocks
) {}
