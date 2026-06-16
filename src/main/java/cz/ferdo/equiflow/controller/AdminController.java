package cz.ferdo.equiflow.controller;

import cz.ferdo.equiflow.dto.MultiStockDTO;
import cz.ferdo.equiflow.dto.ProviderApiKey;
import cz.ferdo.equiflow.dto.StockDTO;
import cz.ferdo.equiflow.dto.StockQuery;
import cz.ferdo.equiflow.service.ApiKeyService;
import cz.ferdo.equiflow.service.StockService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final ApiKeyService apiKeyService;

    public AdminController(ApiKeyService apiKeyService) {
        this.apiKeyService = apiKeyService;
    }

    /**
     *Získá uložený ApyKey podle providera
     * @param provider
     * @return
     */
    @GetMapping("/getkey/{provider}")
    public String getApiKey(@PathVariable String provider) {
        return apiKeyService.getApiKey(provider);
    }

    /**
     * Nastaví a uloží ApyKey podle providera
     * @param apiKey
     * @return
     */
    @PostMapping("/setkey")
    public String setApiKey(@RequestBody ProviderApiKey apiKey) {
        return apiKeyService.setApiKey(apiKey);
    }
}
