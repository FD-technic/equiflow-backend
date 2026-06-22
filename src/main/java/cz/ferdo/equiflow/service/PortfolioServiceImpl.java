package cz.ferdo.equiflow.service;

import cz.ferdo.equiflow.dto.PortfolioDTO;
import cz.ferdo.equiflow.dto.PositionDTO;
import cz.ferdo.equiflow.entity.PortfolioEntity;
import cz.ferdo.equiflow.entity.UserEntity;
import cz.ferdo.equiflow.mapper.PortfolioMapper;
import cz.ferdo.equiflow.mapper.PositionMapper;
import cz.ferdo.equiflow.repository.PortfolioRepository;
import cz.ferdo.equiflow.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PortfolioServiceImpl implements PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final PortfolioMapper portfolioMapper;
    private final PositionMapper positionMapper;
    private final UserRepository userRepository;

    public PortfolioServiceImpl(PortfolioRepository portfolioRepository, PortfolioMapper portfolioMapper, PositionMapper positionMapper, UserRepository userRepository) {
        this.portfolioRepository = portfolioRepository;
        this.portfolioMapper = portfolioMapper;
        this.positionMapper = positionMapper;
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public PortfolioDTO addNew(PortfolioDTO portfolioDTO) {

        PortfolioEntity entity = portfolioMapper.toEntity(portfolioDTO);
        entity.setOwner(findUser(portfolioDTO.getOwnerId()));

        PortfolioEntity saved = portfolioRepository.save(entity);

        return portfolioMapper.toDTO(saved);
    }

    @Override
    public PortfolioDTO getById(Long id) {
        return portfolioMapper.toDTO(findPortfolio(id));
    }

    @Override
    public List<PortfolioDTO> getAll() {

        return portfolioRepository.findAll()
                .stream()
                .map(portfolioMapper::toDTO)
                .toList();
    }

    @Transactional
    @Override
    public PortfolioDTO edit(PortfolioDTO portfolioDTO, Long portfolioId) {

        PortfolioEntity entity = findPortfolio(portfolioId);
        PortfolioEntity newEntity = portfolioMapper.toEntity(portfolioDTO);
        newEntity.setOwner(entity.getOwner());

        return portfolioMapper.toDTO(portfolioRepository.save(newEntity));
    }

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
}
