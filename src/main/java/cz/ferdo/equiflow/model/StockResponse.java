package cz.ferdo.equiflow.model;

import java.time.LocalDateTime;

public record StockResponse(
        Stock stock,
        LocalDateTime lastUpdate
) {}
