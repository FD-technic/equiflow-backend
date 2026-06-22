package cz.ferdo.equiflow.controller;

import cz.ferdo.equiflow.dto.PortfolioDTO;
import cz.ferdo.equiflow.dto.ProviderApiKey;
import cz.ferdo.equiflow.dto.StockDTO;
import cz.ferdo.equiflow.dto.StockQuery;
import cz.ferdo.equiflow.service.ApiKeyService;
import cz.ferdo.equiflow.service.StockService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class StockController {

    private final StockService stockService;
    private final ApiKeyService apiKeyService;

    public StockController(StockService stockService, ApiKeyService apiKeyService) {
        this.apiKeyService = apiKeyService;
        this.stockService = stockService;
    }

    /**
     * provider www.AlphaVantage.com
     * @param query
     */
    @GetMapping("/stocks/av")
    public StockDTO showLiveData(@ModelAttribute StockQuery query) {
        return stockService.getAlphaVantageStock(query);
    }

    /**
     *Získá uložený ApyKey podle providera
     * @param provider
     * @return ApiKey
     */
    @GetMapping("/getkey/{provider}")
    public String getApiKey(@PathVariable String provider) {
        return apiKeyService.getApiKey(provider);
    }

    /**
     * Nastaví a uloží ApyKey podle providera
     * @param apiKey
     * @return Zpráva o uložení
     */
    @PostMapping("/setkey")
    public String setApiKey(@RequestBody ProviderApiKey apiKey) {
        return apiKeyService.setApiKey(apiKey);
    }
}

