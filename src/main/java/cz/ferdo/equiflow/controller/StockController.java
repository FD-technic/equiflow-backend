package cz.ferdo.equiflow.controller;

import cz.ferdo.equiflow.dto.StockDTO;
import cz.ferdo.equiflow.service.StockService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/api/stocks/{ticker}")
    public StockDTO getStockData(
            @PathVariable String ticker,
            @RequestParam(defaultValue = "5") int days
    ) {
        System.out.println(ticker + " " + days);
        return stockService.getStockData(ticker.toUpperCase(), days);
    }
}
