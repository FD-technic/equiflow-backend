package cz.ferdo.equiflow.mapper;

import cz.ferdo.equiflow.dto.PortfolioDTO;
import cz.ferdo.equiflow.entity.PortfolioEntity;
import org.springframework.stereotype.Component;

@Component
public class PortfolioMapper {
    private final PositionMapper positionMapper;

    public PortfolioMapper(PositionMapper positionMapper) {
        this.positionMapper = positionMapper;
    }

    public PortfolioDTO toDTO(PortfolioEntity source) {
        return new PortfolioDTO(
                source.getId(),
                source.getName(),
                source.getType(),
                source.getOwner().getId(),
                source.getOwner().getUserName(),
                source.getPositions()
                        .stream()
                        .map(positionMapper::toDTO)
                        .toList()
        );
    }

    public PortfolioEntity toEntity(PortfolioDTO source) {
        PortfolioEntity entity = new PortfolioEntity();

        entity.setName(source.getName());
        entity.setType(source.getType());
        entity.setPositions(
                source.getPositions()
                        .stream()
                        .map(positionMapper::toEntity)
                        .toList()
        );

        return entity;
    }
}
