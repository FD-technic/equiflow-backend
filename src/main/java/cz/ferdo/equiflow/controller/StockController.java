package cz.ferdo.equiflow.controller;

import cz.ferdo.equiflow.dto.MultiStockDTO;
import cz.ferdo.equiflow.dto.StockDTO;
import cz.ferdo.equiflow.dto.StockQuery;
import cz.ferdo.equiflow.dto.ProviderApiKey;
import cz.ferdo.equiflow.service.ApiKeyService;
import cz.ferdo.equiflow.service.StockService;
import org.springframework.web.bind.annotation.*;

@RestController
public class StockController {

    private final StockService stockService;
    private final ApiKeyService apiKeyService;

    public StockController(StockService stockService, ApiKeyService apiKeyService) {
        this.stockService = stockService;
        this.apiKeyService = apiKeyService;
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
        return apiKeyService.getApiKey(provider);
    }

    /**
     * Nastaví a uloží ApyKey podle providera
     * @param apiKey
     * @return
     */
    @PostMapping("admin/setkey")
    public String setApiKey(@RequestBody ProviderApiKey apiKey) {
        return apiKeyService.setApiKey(apiKey);
    }
}
