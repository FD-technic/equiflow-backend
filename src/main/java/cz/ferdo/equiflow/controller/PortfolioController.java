package cz.ferdo.equiflow.controller;

import cz.ferdo.equiflow.dto.PortfolioDTO;
import cz.ferdo.equiflow.dto.PositionDTO;
import cz.ferdo.equiflow.service.PortfolioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/portfolios")
public class PortfolioController {

    private final PortfolioService portfolioService;

    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    /**
     * Vytvoří nové portfolio a uloží jej do databáze.
     *
     * @param portfolioDTO data nového portfolia
     * @return uložené portfolio včetně přiděleného ID
     */
    @PostMapping()
    public PortfolioDTO addPortfolio(@RequestBody PortfolioDTO portfolioDTO) {
        System.out.println("Owner: " + portfolioDTO.getOwnerId());
        return portfolioService.addNew(portfolioDTO);
    }

    /**
     * Vrátí všechny portfolia.
     *
     * @return seznam portfolií
     */
    @GetMapping()
    public List<PortfolioDTO> getAllPortfolios() {

        return portfolioService.getAll();
    }

    /**
     * Vrátí konkrétní portfolia podle ID.
     *
     * @param portfolioId ID portfolia
     * @return konkrétní portfolio
     */
    @GetMapping("/{portfolioId}")
    public PortfolioDTO findPortfolio(@PathVariable Long portfolioId) {

        return portfolioService.getById(portfolioId);
    }

    /**
     * Smaže konkrétní portfolio podle ID.
     *
     * @param portfolioId ID portfolia
     */
    @DeleteMapping("/{portfolioId}")
    public void removePortfolio(@PathVariable Long portfolioId) {

        portfolioService.remove(portfolioId);
    }

    /**
     * Aktualizuje existující portfolio.
     *
     * @param portfolioDTO nová data portfolia
     * @param portfolioId ID upravovaného portfolia
     * @return aktualizované portfolio
     */
    @PutMapping("/{portfolioId}")
    public PortfolioDTO editPortfolio(@RequestBody PortfolioDTO portfolioDTO, @PathVariable Long portfolioId) {

        return portfolioService.edit(portfolioDTO, portfolioId);
    }

    /**
     * Přidá novou pozici do portfolia.
     * Pokud portfolio již obsahuje stejný ticker,
     * navýší množství a přepočítá průměrnou nákupní cenu.
     *
     * @param portfolioId ID portfolia
     * @param positionDTO data nové pozice
     * @return aktualizované portfolio
     */
    @PostMapping("/{portfolioId}/positions")
    public PortfolioDTO addPosition(@PathVariable Long portfolioId,
                                         @RequestBody PositionDTO positionDTO) {
        return portfolioService.addPosition(portfolioId, positionDTO);
    }

    /**
     * Odstraní pozici z portfolia podle ID.
     *
     * @param portfolioId ID portfolia
     * @param positionId ID odstraňované pozice
     */
    @DeleteMapping("/{portfolioId}/positions/{positionId}") public void removePosition(@PathVariable Long positionId, @PathVariable Long portfolioId) {
        portfolioService.removePosition(positionId, portfolioId);
    }
}
