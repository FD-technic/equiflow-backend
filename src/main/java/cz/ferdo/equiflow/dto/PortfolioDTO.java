package cz.ferdo.equiflow.dto;

import cz.ferdo.equiflow.model.PortfolioType;

import java.util.List;

public class PortfolioDTO {

    private Long id;
    private String name;
    private PortfolioType type;
    private Long ownerId;
    private String ownerName;
    private List<PositionDTO> positions;

    public PortfolioDTO(Long id, String name, PortfolioType type, Long ownerId, String ownerName, List<PositionDTO> positions) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.ownerId = ownerId;
        this.ownerName = ownerName;
        this.positions = positions;
    }

    // === Getter / Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PortfolioType getType() {
        return type;
    }

    public void setType(PortfolioType type) {
        this.type = type;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public List<PositionDTO> getPositions() {
        return positions;
    }

    public void setPositions(List<PositionDTO> positions) {
        this.positions = positions;
    }

}

