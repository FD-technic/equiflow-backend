package cz.ferdo.equiflow.service;

import cz.ferdo.equiflow.dto.StockDTO;
import cz.ferdo.equiflow.dto.StockPointDTO;
import cz.ferdo.equiflow.model.StockPoint;
import cz.ferdo.equiflow.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;


    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public StockDTO getStockData(String ticker, int days) {
        List<StockPoint> allData = stockRepository.findBySymbol(ticker.toUpperCase());

        if (allData.isEmpty()) {
            return new StockDTO(ticker, "USD", List.of());
        }

        if (days > 0 && days < allData.size()) {
            allData = allData.subList(allData.size() - days, allData.size());
        }

        List<StockPointDTO> points = allData.stream()
                .map(p -> new StockPointDTO(p.getDate(), p.getPrice()))
                .toList();

        return new StockDTO(ticker, "USD", points);
    }
}
