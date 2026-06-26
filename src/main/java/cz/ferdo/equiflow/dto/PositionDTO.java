package cz.ferdo.equiflow.dto;

import cz.ferdo.equiflow.model.Provider;

import java.math.BigDecimal;

public record PositionDTO(

        Provider provider,
        String ticker,
        long quantity,
        BigDecimal buyPrice
) {}
