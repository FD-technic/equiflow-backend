package cz.ferdo.equiflow.repository;

import cz.ferdo.equiflow.dto.MultiStockDTO;
import cz.ferdo.equiflow.dto.StockDTO;
import cz.ferdo.equiflow.dto.StockPointDTO;
import cz.ferdo.equiflow.mapper.StockPointMapper;
import cz.ferdo.equiflow.model.Stock;
import cz.ferdo.equiflow.model.StockPoint;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class InMemoryStockRepository implements StockRepository {

    private final Map<String, List<StockPoint>> data = Map.of(
            "SPY", List.of(
                    new StockPoint("2005-02-25", 93.6948),
                    new StockPoint("2005-02-28", 93.0544),
                    new StockPoint("2005-03-01", 93.5386),
                    new StockPoint("2005-03-02", 93.4927),
                    new StockPoint("2005-03-03", 93.5301),
                    new StockPoint("2005-03-04", 94.7073),
                    new StockPoint("2005-03-07", 94.7456),
                    new StockPoint("2005-03-08", 94.3665),
                    new StockPoint("2005-03-09", 93.3232),
                    new StockPoint("2005-03-10", 93.5386),
                    new StockPoint("2005-03-11", 72.8868),
                    new StockPoint("2005-03-14", 93.4640),
                    new StockPoint("2005-03-15", 92.6812),
                    new StockPoint("2005-03-16", 91.9098),
                    new StockPoint("2005-03-17", 92.1010),
                    new StockPoint("2005-03-18", 91.4504),
                    new StockPoint("2005-03-21", 91.1221),
                    new StockPoint("2005-03-22", 90.2002),
                    new StockPoint("2005-03-23", 90.2740),
                    new StockPoint("2005-03-24", 90.3851),
                    new StockPoint("2005-03-28", 90.5114),
                    new StockPoint("2005-03-29", 89.9035),
                    new StockPoint("2005-03-30", 91.1841),
                    new StockPoint("2005-03-31", 91.0088),
                    new StockPoint("2005-04-01", 90.6001),
                    new StockPoint("2005-04-04", 90.7544),
                    new StockPoint("2005-04-05", 91.1938),
                    new StockPoint("2005-04-06", 91.4970),
                    new StockPoint("2005-04-07", 92.0007),
                    new StockPoint("2005-04-08", 91.0378)
            ),
            "AAPL", List.of(
                    new StockPoint("2005-02-25", 3.6948),
                    new StockPoint("2005-02-28", 3.0544),
                    new StockPoint("2005-03-01", 3.5386),
                    new StockPoint("2005-03-02", 3.4927),
                    new StockPoint("2005-03-03", 3.5301),
                    new StockPoint("2005-03-04", 4.7073),
                    new StockPoint("2005-03-07", 4.7456),
                    new StockPoint("2005-03-08", 4.3665),
                    new StockPoint("2005-03-09", 3.3232),
                    new StockPoint("2005-03-10", 3.5386),
                    new StockPoint("2005-03-11", 12.8868),
                    new StockPoint("2005-03-14", 3.4640),
                    new StockPoint("2005-03-15", 2.6812),
                    new StockPoint("2005-03-16", 1.9098),
                    new StockPoint("2005-03-17", 2.1010),
                    new StockPoint("2005-03-18", 1.4504),
                    new StockPoint("2005-03-21", 1.1221),
                    new StockPoint("2005-03-22", 0.2002),
                    new StockPoint("2005-03-23", 0.2740),
                    new StockPoint("2005-03-24", 0.3851),
                    new StockPoint("2005-03-28", 0.5114),
                    new StockPoint("2005-03-29", 0.9035),
                    new StockPoint("2005-03-30", 1.1841),
                    new StockPoint("2005-03-31", 1.0088),
                    new StockPoint("2005-04-01", 0.6001),
                    new StockPoint("2005-04-04", 0.7544),
                    new StockPoint("2005-04-05", 1.1938),
                    new StockPoint("2005-04-06", 1.4970),
                    new StockPoint("2005-04-07", 2.0007),
                    new StockPoint("2005-04-08", 1.0378)
            )
    );

    @Override
    public Stock findBySymbol(String symbol) {
        return new Stock(
                symbol,
                data.getOrDefault(symbol.toUpperCase(), List.of()), "USD");
    }

    @Override
    public MultiStockDTO getAll() {

        List<StockDTO> stocks = data.entrySet()
                .stream()
                .map((stock) -> {
                    return new StockDTO(
                            stock.getKey(), "",
                            stock.getValue()
                                    .stream()
                                    .map(StockPointMapper::toDTO)
                                    .toList());
                })
                .toList();
        return new MultiStockDTO(stocks);
    }
}
