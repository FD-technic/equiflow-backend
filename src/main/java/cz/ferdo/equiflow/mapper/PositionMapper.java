package cz.ferdo.equiflow.mapper;

import cz.ferdo.equiflow.dto.PositionDTO;
import cz.ferdo.equiflow.entity.PositionEntity;
import org.springframework.stereotype.Service;

@Service
public class PositionMapper {
    public PositionDTO toDTO (PositionEntity source) {
        return new PositionDTO(
                source.getStock().getProvider(),
                source.getStock().getTicker(),
                source.getQuantity(),
                source.getBuyPrice()
        );
    }

    public PositionEntity toEntity(PositionDTO source) {
        PositionEntity entity = new PositionEntity();

        entity.setQuantity(source.quantity());
        entity.setBuyPrice(source.buyPrice());

        return entity;
    }
}
