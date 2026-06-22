package cz.ferdo.equiflow.service;

import cz.ferdo.equiflow.dto.PortfolioDTO;
import cz.ferdo.equiflow.dto.PositionDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PortfolioService {

    /**
     * Vytvoří nové portfolio a přiřadí jej existujícímu uživateli.
     *
     * @param portfolioDTO data nového portfolia
     * @return uložené portfolio
     */
    PortfolioDTO addNew(PortfolioDTO portfolioDTO);

    /**
     * Vyhledá portfolio podle ID.
     *
     * @param id ID portfolia
     * @return nalezené portfolio
     */
    PortfolioDTO getById(Long id);

    /**
     * Vrátí všechna uložená portfolia.
     *
     * @return seznam portfolií
     */
    List<PortfolioDTO> getAll();

    /**
     * Aktualizuje údaje existujícího portfolia.
     *
     * @param portfolioDTO nová data portfolia
     * @param portfolioId ID upravovaného portfolia
     * @return aktualizované portfolio
     */
    PortfolioDTO edit(PortfolioDTO portfolioDTO, Long portfolioId);

    /**
     * Smaže portfolio podle ID.
     *
     * @param portfolioId ID mazaného portfolia
     */
    void remove(Long portfolioId);

    /**
     * Přidá pozici do portfolia.
     * Pokud portfolio již obsahuje stejný ticker,
     * navýší množství a přepočítá průměrnou nákupní cenu.
     *
     * @param portfolioId ID portfolia
     * @param positionDTO data přidávané pozice
     * @return aktualizované portfolio
     */
    PortfolioDTO addPosition(Long portfolioId, PositionDTO positionDTO);

    /**
     * Odstraní pozici z portfolia.
     *
     * @param positionId ID odstraňované pozice
     * @param portfolioId ID portfolia
     */
    void removePosition(Long positionId, Long portfolioId);
}
