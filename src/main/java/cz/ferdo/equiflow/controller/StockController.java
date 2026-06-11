package cz.ferdo.equiflow.controller;

import cz.ferdo.equiflow.dto.MultiStockDTO;
import cz.ferdo.equiflow.dto.StockDTO;
import cz.ferdo.equiflow.dto.StockQuery;
import cz.ferdo.equiflow.config.ProviderApiKey;
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
     * provider www.AlphaVantage.com
     * @param query
     */
    @GetMapping("/api/stocks/av")
    public StockDTO showLiveData(@ModelAttribute StockQuery query) {
        System.out.println("BUILD 2026-06-11");
        return stockService.getAlphaVantageStock(query);
    }

    /**
     *Získá uložený ApyKey podle providera
     * @param provider
     * @return
     */
    @GetMapping("admin/getkey/{provider}")
    public String getApiKey(@PathVariable String provider) {
        return stockService.getApiKey(provider);
    }

    /**
     * Nastaví a uloží ApyKey podle providera
     * @param apiKey
     * @return
     */
    @PostMapping("admin/setkey")
    public String setApiKey(@RequestBody ProviderApiKey apiKey) {
        return stockService.setApiKey(apiKey);
    }
}
