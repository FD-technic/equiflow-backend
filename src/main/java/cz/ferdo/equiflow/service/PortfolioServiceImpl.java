package cz.ferdo.equiflow.service;

import cz.ferdo.equiflow.dto.*;
import cz.ferdo.equiflow.entity.PortfolioEntity;
import cz.ferdo.equiflow.entity.UserEntity;
import cz.ferdo.equiflow.mapper.PortfolioMapper;
import cz.ferdo.equiflow.mapper.PositionMapper;
import cz.ferdo.equiflow.model.Interval;
import cz.ferdo.equiflow.model.Period;
import cz.ferdo.equiflow.repository.PortfolioRepository;
import cz.ferdo.equiflow.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class PortfolioServiceImpl implements PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final PortfolioMapper portfolioMapper;
    private final PositionMapper positionMapper;
    private final UserRepository userRepository;
    private final StockService stockService;

    public PortfolioServiceImpl(PortfolioRepository portfolioRepository, PortfolioMapper portfolioMapper, PositionMapper positionMapper, UserRepository userRepository, StockService stockService) {
        this.portfolioRepository = portfolioRepository;
        this.portfolioMapper = portfolioMapper;
        this.positionMapper = positionMapper;
        this.userRepository = userRepository;
        this.stockService = stockService;
    }

    @Transactional
    @Override
    public PortfolioDTO addNew(PortfolioDTO portfolioDTO) {

        PortfolioEntity entity = portfolioMapper.toEntity(portfolioDTO);
        entity.setOwner(findUser(portfolioDTO.getOwnerId()));

        PortfolioEntity saved = portfolioRepository.save(entity);

        return portfolioMapper.toDTO(saved);
    }

    @Transactional
    @Override
    public PortfolioDetailDTO findById(Long id) {

        PortfolioDTO portfolio = portfolioMapper.toDTO(findPortfolio(id));

        List<PositionDetailDTO> positions = new ArrayList<>();
        for (PositionDTO p : portfolio.getPositions()) {
            StockQuery query = new StockQuery(p.provider(), p.ticker(), Period.DAY, Interval.DAILY);
            positions.add(createPositionDetail(p, query));
        }

        return new PortfolioDetailDTO(
                portfolio.getId(),
                portfolio.getName(),
                portfolio.getType(),
                portfolio.getOwnerId(),
                portfolio.getOwnerName(),
                positions
        );
    }

    @Override
    public List<PortfolioListDTO> findAll() {

        return portfolioRepository.findPortfolioList();
    }

    @Transactional
    @Override
    public PortfolioDTO edit(PortfolioDTO portfolioDTO, Long portfolioId) {

        PortfolioEntity entity = findPortfolio(portfolioId);
        PortfolioEntity newEntity = portfolioMapper.toEntity(portfolioDTO);
        newEntity.setOwner(entity.getOwner());

        return portfolioMapper.toDTO(portfolioRepository.save(newEntity));
    }

    @Transactional
    @Override
    public PortfolioDTO addPosition(Long portfolioId, PositionDTO positionDTO) {
        PortfolioEntity entity = findPortfolio(portfolioId);
        entity.addPosition(positionMapper.toEntity(positionDTO));

        PortfolioEntity saved = portfolioRepository.save(entity);
        return portfolioMapper.toDTO(saved);
    }

    @Override
    public void removePosition(Long positionId, Long portfolioId) {
        PortfolioEntity entity = findPortfolio(portfolioId);

        entity.getPositions()
                .removeIf(e -> e.getId().equals(positionId));

        portfolioRepository.save(entity);
    }


    @Override
    public void remove(Long portfolioId) {
        portfolioRepository.delete(findPortfolio(portfolioId));
    }

    // === PRIVATE ===

    private PortfolioEntity findPortfolio(Long id) {

        return portfolioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Portfolio not found"));
    }

    private UserEntity findUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private PositionDetailDTO createPositionDetail(PositionDTO positionDTO, StockQuery query) {
        StockDTO stockDTO = stockService.findByQuery(query);
        BigDecimal buyPrice = positionDTO.buyPrice();
        BigDecimal currentPrice = stockDTO.points().getLast().close();
        BigDecimal quantity = BigDecimal.valueOf(positionDTO.quantity());
        BigDecimal investValue = quantity.multiply(buyPrice);
        BigDecimal currentValue = quantity.multiply(currentPrice);
        BigDecimal profit = currentValue.subtract(investValue);

        return new PositionDetailDTO(
                positionDTO.ticker(),
                positionDTO.quantity(),
                buyPrice,
                currentPrice,
                investValue,
                currentValue,
                profit,
                profit
                        .divide(investValue, 6, RoundingMode.HALF_EVEN)
                        .multiply(BigDecimal.valueOf(100))
                        .setScale(2, RoundingMode.HALF_EVEN)
        );
    }
}
