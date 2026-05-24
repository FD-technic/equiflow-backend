package cz.ferdo.equiflow.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import cz.ferdo.equiflow.dto.MultiStockDTO;
import cz.ferdo.equiflow.dto.StockDTO;
import cz.ferdo.equiflow.dto.StockFileDTO;
import cz.ferdo.equiflow.mapper.StockPointMapper;
import cz.ferdo.equiflow.model.Stock;
import cz.ferdo.equiflow.model.StockPoint;
import org.springframework.stereotype.Repository;


import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InMemoryStockRepository implements StockRepository {

    private final Map<String, List<StockPoint>> data = new HashMap<>();

    private final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());


    public InMemoryStockRepository() {

        loadStockFile("data.json");
    }

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

    @Override
    public void loadStockFile(String fileName) {
        try {
            InputStream input = getClass().getResourceAsStream("/data/" + fileName);


            System.out.println("INPT: " + input);
            StockFileDTO stockFile = mapper.readValue(input, StockFileDTO.class);

            List<StockPoint> points = stockFile.points()
                    .stream()
                    .map(point -> new StockPoint(
                            point.date(),
                            point.price()
                    ))
                    .toList();

            data.put(stockFile.ticker(), points);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
     }
}
