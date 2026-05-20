package cz.ferdo.equiflow.mapper;

import cz.ferdo.equiflow.dto.StockPointDTO;
import cz.ferdo.equiflow.model.StockPoint;

public class StockPointMapper {

    public static StockPointDTO toDTO(StockPoint point) {
        return new StockPointDTO(
                point.getDate(),
                point.getPrice()
        );
    };
}
