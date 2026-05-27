package cz.ferdo.equiflow.controller;

import cz.ferdo.equiflow.dto.MultiStockDTO;
import cz.ferdo.equiflow.dto.StockDTO;
import cz.ferdo.equiflow.dto.StockQuery;
import cz.ferdo.equiflow.model.Stock;
import cz.ferdo.equiflow.service.StockService;
import org.springframework.web.bind.annotation.*;

@RestController
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/api")
    public MultiStockDTO getAll() {
        return stockService.getAll();
    }

    @GetMapping("/api/stocks/{ticker}")
    public StockDTO getStockData(
            @PathVariable String ticker,
            @RequestParam(defaultValue = "5") int days
    ) {
        System.out.println(ticker + " " + days);
        return stockService.getStockData(ticker.toUpperCase(), days);
    }

    /**
     * AlphaVantage.co
     * @param query
     */
    @GetMapping("/api/av/stocks")
    public Stock showLiveData(@ModelAttribute StockQuery query) {
        return stockService.getLiveTicker(query);
    }
}
