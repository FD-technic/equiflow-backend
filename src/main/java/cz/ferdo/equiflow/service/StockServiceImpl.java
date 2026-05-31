package cz.ferdo.equiflow.service;

import cz.ferdo.equiflow.dto.MultiStockDTO;
import cz.ferdo.equiflow.dto.StockQuery;
import cz.ferdo.equiflow.model.ProviderApiKey;
import cz.ferdo.equiflow.model.Stock;
import cz.ferdo.equiflow.model.StockResponse;
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
import java.time.temporal.ChronoUnit;

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
    public StockResponse getLocalData(String ticker, int days) {
        Path path = Paths.get("cache/" + ticker.toUpperCase() + ".json");

        Stock stock = null;

        try {
           stock = alphaVantageProvider.fetchStock(Files.readString(path));
        } catch (IOException e) {
            System.out.println("Data not exist");
        }

        return new StockResponse(stock, lastUpdate(path));
    }

    @Override
    public StockResponse getAlphaVantageStock(StockQuery query) {
        Stock stock;
        Path path = Paths.get("cache/" + query.ticker().toUpperCase() + ".json");

        if (!cacheValid(path)) {
            try {
                Files.createDirectories(Paths.get("cache"));
                Files.writeString(path, alphaVantageProvider.fetchRawJson(query));
                System.out.println("Loaded fresh data from AlphaVantage");
            } catch (Exception e) {
                System.out.println("AlphaVantage unavailable, using cached data.");
            }
        }

        try {
            stock = alphaVantageProvider.fetchStock(Files.readString(path));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return new StockResponse(stock, lastUpdate(path));
    }

    @Override
    public String setApiKey(ProviderApiKey apiKey) {
        Path path = Paths.get(
                "settings/" + apiKey.provider() + ".key"
        );

        try {
            Files.createDirectories(Paths.get("settings"));
            Files.writeString(path, apiKey.apiKey());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "API key for provider " + apiKey.provider() + " saved";
    }

    private boolean cacheValid(Path path) {
        if (Files.exists(path)) {

            LocalDateTime today = LocalDateTime.now();

            return lastUpdate(path).isAfter(today.minusDays(1));
        }
        return false;
    }

    private LocalDateTime lastUpdate(Path path) {
        LocalDateTime modified;

        try {
            FileTime time = Files.getLastModifiedTime(path);
            modified = time.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return modified.truncatedTo(ChronoUnit.SECONDS);
    }
}
