package cz.ferdo.equiflow.mapper;

import cz.ferdo.equiflow.dto.StockPointDTO;
import cz.ferdo.equiflow.entity.StockPointEntity;

public class StockPointMapper {

    public static StockPointDTO toDTO(StockPointEntity point) {
        return new StockPointDTO(
                point.getDate(),
                point.getOpen(),
                point.getHigh(),
                point.getLow(),
                point.getClose(),
                point.getVolume()
        );
    };

    public static StockPointEntity toEntity(StockPointDTO point) {

        StockPointEntity pointEntity = new StockPointEntity();

        pointEntity.setDate(point.date());
        pointEntity.setOpen(point.open());
        pointEntity.setHigh(point.high());
        pointEntity.setLow(point.low());
        pointEntity.setClose(point.close());
        pointEntity.setVolume(point.volume());

        return pointEntity;
    };
}
