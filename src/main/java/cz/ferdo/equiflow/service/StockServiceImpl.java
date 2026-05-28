package cz.ferdo.equiflow.service;

import cz.ferdo.equiflow.dto.MultiStockDTO;
import cz.ferdo.equiflow.dto.StockDTO;
import cz.ferdo.equiflow.dto.StockQuery;
import cz.ferdo.equiflow.mapper.StockPointMapper;
import cz.ferdo.equiflow.model.Stock;
import cz.ferdo.equiflow.provider.AlphaVantageProvider;
import cz.ferdo.equiflow.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    private final AlphaVantageProvider alphaVantageProvider;


    public StockServiceImpl(StockRepository stockRepository, AlphaVantageProvider alphaVantageProvider) {
        this.stockRepository = stockRepository;
        this.alphaVantageProvider = alphaVantageProvider;
    }

    @Override
    public MultiStockDTO getAll() {

        return stockRepository.getAll();
    }

    @Override
    public StockDTO getStockData(String ticker, int days) {
        Stock stockData = stockRepository.findBySymbol(ticker.toUpperCase());

        return new StockDTO(ticker, "USD", stockData.getPoints()
                .stream()
                .map(StockPointMapper::toDTO)
                .toList());
    }

    @Override
    public Stock getLiveTicker(StockQuery query) {
        Stock response;
        String json = "";
        Path path = Paths.get("cache/" + query.ticker().toUpperCase() + ".json");

        if (!cacheValid(path)) {
            try {
                Files.createDirectories(Paths.get("cache"));
                json = alphaVantageProvider.fetchRawJson(query);
                Files.writeString(path, json);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        try {
            response = alphaVantageProvider.fetchStock(Files.readString(path));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return response;
    }

    private boolean cacheValid(Path path) {
        if (Files.exists(path)) {
            LocalDateTime modified;
            LocalDateTime today = LocalDateTime.now();

            try {
                FileTime time = Files.getLastModifiedTime(path);
                modified = time.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (modified.isAfter(today.minusDays(1))) {
                return true;
            }
        }
        return false;
    }
}
