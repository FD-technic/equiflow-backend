package cz.ferdo.equiflow.mapper;

import cz.ferdo.equiflow.dto.StockDTO;
import cz.ferdo.equiflow.entity.StockEntity;
import org.springframework.stereotype.Component;

@Component
public class StockMapper {
    public StockDTO toDTO(StockEntity source) {
        return new StockDTO(
                source.getTicker(),
                source.getCurrency(),
                source.getPoints()
                        .stream()
                        .map(StockPointMapper::toDTO)
                        .toList(),
                source.getUpdateAt()
        );
    }

    public StockEntity toEntity(StockDTO source) {

        StockEntity entity = new StockEntity();

        entity.setTicker(source.ticker());
        entity.setCurrency(source.currency());
        entity.setUpdateAt(source.updateAt());
        entity.setPoints(source.points()
                .stream()
                .map(StockPointMapper::toEntity)
                .toList()
        );

        return entity;
    }

    public void updateEntity( StockDTO source, StockEntity target) {
        target.setCurrency(source.currency());
        target.setUpdateAt(source.updateAt());

        target.setPoints(
                source.points()
                        .stream()
                        .map(StockPointMapper::toEntity)
                .toList()
        );
    }
}
