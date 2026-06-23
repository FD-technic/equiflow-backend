package cz.ferdo.equiflow.dto;

import cz.ferdo.equiflow.model.PortfolioType;

import java.util.List;

public record PortfolioDetailDTO(
        Long id,
        String name,
        PortfolioType type,
        Long owner,
        String ownerName,
        List<PositionDetailDTO> positionDetailDTO
) {
}
