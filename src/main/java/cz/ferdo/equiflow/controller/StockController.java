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


    /**
     * AlphaVantage.co
     * @param query
     */
    @GetMapping("/api/stocks/av")
    public Stock showLiveData(@ModelAttribute StockQuery query) {
        return stockService.getLiveTicker(query);
    }
}
