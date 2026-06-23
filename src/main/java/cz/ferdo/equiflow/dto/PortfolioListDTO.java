package cz.ferdo.equiflow.dto;

import cz.ferdo.equiflow.model.PortfolioType;

public record PortfolioListDTO(
        Long id,
        PortfolioType type,
        String name,
        Long ownerId,
        String ownerName,
        Long positionCount
) {
}
