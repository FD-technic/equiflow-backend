package cz.ferdo.equiflow.controller;

import cz.ferdo.equiflow.dto.MultiStockDTO;
import cz.ferdo.equiflow.dto.StockDTO;
import cz.ferdo.equiflow.dto.StockQuery;
import cz.ferdo.equiflow.dto.ProviderApiKey;
import cz.ferdo.equiflow.service.ApiKeyService;
import cz.ferdo.equiflow.service.StockService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping()
    public MultiStockDTO getAll() {
        return stockService.getAll();
    }

    /**
     * provider www.AlphaVantage.com
     * @param query
     */
    @GetMapping("/av")
    public StockDTO showLiveData(@ModelAttribute StockQuery query) {
        System.out.println("BUILD 2026-06-11");
        return stockService.getAlphaVantageStock(query);
    }
}
